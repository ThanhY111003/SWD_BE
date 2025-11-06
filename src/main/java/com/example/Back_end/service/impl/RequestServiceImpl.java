package com.example.Back_end.service.impl;

import com.example.Back_end.dto.RequestRequestDTO;
import com.example.Back_end.dto.RequestResponseDTO;
import com.example.Back_end.entity.*;
import com.example.Back_end.enums.RequestStatus;
import com.example.Back_end.enums.RequestTypeEnum;
import com.example.Back_end.repository.*;
import com.example.Back_end.service.interf.NotificationService;
import com.example.Back_end.service.interf.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepo;
    private final MemberRepository memberRepo;
    private final StaffRepository staffRepo;
    private final LabRepository labRepo;
    private final RoomRepository roomRepo;
    private final RoomSlotRepository roomSlotRepo;
    private final SupporterRepository supporterRepo;
    private final SupporterShiftRepository supporterShiftRepo;
    private final NotificationService notificationService;

    // =======================================================================
    //                               CRUD
    // =======================================================================

    @Override
    public List<RequestResponseDTO> getAll() {
        return requestRepo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RequestResponseDTO getById(Long id) {
        return requestRepo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public RequestResponseDTO create(RequestRequestDTO dto) {
        Request request = new Request();

        request.setTitle(dto.getTitle());
        request.setDescription(dto.getDescription());
        request.setRequestedAt(LocalDateTime.now());
        request.setStatus(RequestStatus.PENDING);

        // ✅ parse enum từ String
        try {
            request.setRequestType(RequestTypeEnum.valueOf(dto.getRequestType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid request type: " + dto.getRequestType());
        }

        request.setMember(memberRepo.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found")));
        request.setLab(labRepo.findById(dto.getLabId())
                .orElseThrow(() -> new RuntimeException("Lab not found")));

        // ------------------------------------------------------------
        // CASE 1: BOOKING
        // ------------------------------------------------------------
        if (request.getRequestType() == RequestTypeEnum.BOOKING && dto.getRoomSlotIds() != null) {
            request.setRoomSlots(dto.getRoomSlotIds().stream()
                    .map(id -> roomSlotRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("RoomSlot not found")))
                    .collect(Collectors.toList()));

            // Gán staff phụ trách lab (nếu có)
            Staff staff = staffRepo.findFirstByLabs_LabId(request.getLab().getLabId()).orElse(null);
            if (staff != null) {
                request.setStaff(staff);
            }
        }

        // ------------------------------------------------------------
        // CASE 2: OPEN_DOOR
        // ------------------------------------------------------------
        else if (request.getRequestType() == RequestTypeEnum.OPEN_DOOR
                && dto.getRoomSlotIds() != null && !dto.getRoomSlotIds().isEmpty()) {

            RoomSlot rs = roomSlotRepo.findById(dto.getRoomSlotIds().get(0))
                    .orElseThrow(() -> new RuntimeException("RoomSlot not found"));

            Supporter supporter = findAvailableSupporterForRoomSlot(rs);
            if (supporter != null) {
                request.setSupporter(supporter);
            }
        }

        // ✅ Gửi thông báo cho staff trong lab
        Staff notifyStaff = staffRepo.findFirstByLabs_LabId(request.getLab().getLabId()).orElse(null);
        if (notifyStaff != null) {
            notificationService.notifyStaff(
                    notifyStaff,
                    "Yêu cầu mới từ " + request.getMember().getMemberCode(),
                    "Loại yêu cầu: " + request.getRequestType() + " - " + request.getTitle()
            );
        }

        return toResponse(requestRepo.save(request));
    }

    @Override
    public RequestResponseDTO update(Long id, RequestRequestDTO dto) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        RequestStatus newStatus = RequestStatus.valueOf(dto.getStatus().toUpperCase());
        request.setStatus(newStatus);

        // ------------------------------------------------------------
        // APPROVED
        // ------------------------------------------------------------
        if (newStatus == RequestStatus.APPROVED) {
            request.setApprovedAt(LocalDateTime.now());

            if (request.getRequestType() == RequestTypeEnum.BOOKING) {
                if (dto.getRoomId() != null) {
                    Room room = roomRepo.findById(dto.getRoomId())
                            .orElseThrow(() -> new RuntimeException("Room not found"));

                    // ✅ Chặn nếu phòng đang sử dụng
                    if ("IN_USE".equalsIgnoreCase(room.getStatus())) {
                        throw new RuntimeException("Phòng này đang được sử dụng, không thể duyệt thêm yêu cầu mới!");
                    }

                    // ✅ Kiểm tra supporter trực
                    if (dto.getRoomSlotIds() != null && !dto.getRoomSlotIds().isEmpty()) {
                        RoomSlot rs = roomSlotRepo.findById(dto.getRoomSlotIds().get(0))
                                .orElseThrow(() -> new RuntimeException("RoomSlot not found"));

                        Supporter supporter = findAvailableSupporterForRoomSlot(rs);
                        if (supporter == null) {
                            throw new RuntimeException("Không có supporter trực trong ca này — không thể duyệt yêu cầu!");
                        }
                        request.setSupporter(supporter);
                    }

                    // ✅ Đánh dấu phòng đang dùng
                    room.setStatus("IN_USE");
                    roomRepo.save(room);

                    request.setRoom(room);
                }

                Staff staff = staffRepo.findFirstByLabs_LabId(request.getLab().getLabId()).orElse(null);
                if (staff != null) {
                    request.setStaff(staff);
                }
            }
            else if (request.getRequestType() == RequestTypeEnum.OPEN_DOOR
                    && dto.getRoomSlotIds() != null && !dto.getRoomSlotIds().isEmpty()) {

                RoomSlot rs = roomSlotRepo.findById(dto.getRoomSlotIds().get(0))
                        .orElseThrow(() -> new RuntimeException("RoomSlot not found"));

                Supporter supporter = findAvailableSupporterForRoomSlot(rs);
                if (supporter != null) {
                    request.setSupporter(supporter);
                }
            }
        }

        // ------------------------------------------------------------
        // REJECTED
        // ------------------------------------------------------------
        else if (newStatus == RequestStatus.REJECTED) {
            if (request.getRoom() != null) {
                Room room = request.getRoom();
                room.setStatus("ACTIVE"); // ✅ Trả lại phòng
                roomRepo.save(room);
            }
            request.setRoom(null);
            request.setSupporter(null);
        }

        // ------------------------------------------------------------
        // COMPLETED
        // ------------------------------------------------------------
        else if (newStatus == RequestStatus.COMPLETED) {
            request.setCompletedAt(LocalDateTime.now());

            if (request.getRoom() != null) {
                Room room = request.getRoom();
                room.setStatus("ACTIVE"); // ✅ Hoàn thành thì phòng rảnh
                roomRepo.save(room);
            }
        }

        return toResponse(requestRepo.save(request));
    }


    @Override
    public void delete(Long id) {
        requestRepo.deleteById(id);
    }

    // =======================================================================
    //                           STAFF APPROVAL API
    // =======================================================================

    public RequestResponseDTO approveByStaff(Long staffId, Long requestId) {
        Request request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));


        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        // ✅ Kiểm tra quyền: staff phải thuộc lab của request
        boolean hasPermission = staff.getLabs().stream()
                .anyMatch(lab -> lab.getLabId().equals(request.getLab().getLabId()));
        if (!hasPermission) {
            throw new RuntimeException("Staff không có quyền duyệt request này");
        }

        // ✅ Chỉ xử lý BOOKING
        if (request.getRequestType() != RequestTypeEnum.BOOKING) {
            throw new RuntimeException("Chỉ có thể duyệt request loại BOOKING");
        }

        // ✅ Cập nhật trạng thái
        request.setStatus(RequestStatus.APPROVED);
        request.setApprovedAt(LocalDateTime.now());
        request.setStaff(staff);

        // ✅ Gán phòng ACTIVE trong lab
        Room activeRoom = roomRepo.findFirstByLabAndStatus(request.getLab(), "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Không có phòng ACTIVE trong lab"));

        activeRoom.setStatus("IN_USE");
        roomRepo.save(activeRoom);

        request.setRoom(activeRoom);

        // ✅ Tìm supporter phù hợp với thời gian booking
        Supporter supporter = findAvailableSupporterForRequest(request);
        if (supporter == null) {
            throw new RuntimeException("Không có supporter trực trong ca này — không thể duyệt yêu cầu!");
        }

        request.setSupporter(supporter);

        // ✅ Gửi thông báo cho supporter
        notificationService.notifySupporter(
                supporter,
                "Yêu cầu mở cửa phòng",
                "Có yêu cầu được duyệt tại " + activeRoom.getRoomName() +
                        " (" + request.getTitle() + ")"
        );


        // ✅ Gửi thông báo cho member
        notificationService.notifyMember(
                request.getMember(),
                "Yêu cầu của bạn đã được duyệt",
                "Yêu cầu '" + request.getTitle() + "' đã được duyệt. Phòng: " + activeRoom.getRoomName()
        );

        return toResponse(requestRepo.save(request));
    }

    // =======================================================================
    //                           HELPER FUNCTIONS
    // =======================================================================

    private Supporter findAvailableSupporterForRequest(Request request) {
        if (request.getRoomSlots() == null || request.getRoomSlots().isEmpty()) return null;

        var bookingDate = request.getRoomSlots().get(0).getBookingDate();
        var minStart = request.getRoomSlots().stream()
                .map(RoomSlot::getStartTime)
                .min(LocalTime::compareTo)
                .orElse(null);
        var maxEnd = request.getRoomSlots().stream()
                .map(RoomSlot::getEndTime)
                .max(LocalTime::compareTo)
                .orElse(null);

        var shifts = supporterShiftRepo.findAvailableShiftsForSlot(bookingDate, minStart, maxEnd);
        if (shifts.isEmpty()) return null;

        var shift = shifts.get(0);
        if (shift.getSupporters() != null && !shift.getSupporters().isEmpty()) {
            return shift.getSupporters().get(0);
        }
        return null;
    }

    private Supporter findAvailableSupporterForRoomSlot(RoomSlot rs) {
        var shifts = supporterShiftRepo.findAvailableShiftsForSlot(
                rs.getBookingDate(), rs.getStartTime(), rs.getEndTime());
        if (shifts.isEmpty()) return null;

        var shift = shifts.get(0);
        if (shift.getSupporters() != null && !shift.getSupporters().isEmpty()) {
            return shift.getSupporters().get(0);
        }
        return null;
    }

    private RequestResponseDTO toResponse(Request r) {
        RequestResponseDTO dto = new RequestResponseDTO();
        dto.setRequestId(r.getRequestId());
        dto.setRequestType(r.getRequestType() != null ? r.getRequestType().name() : null);
        dto.setMemberName(r.getMember() != null ? r.getMember().getMemberCode() : null);
        dto.setStaffName(r.getStaff() != null ? r.getStaff().getStaffCode() : null);
        dto.setSupporterName(r.getSupporter() != null ? r.getSupporter().getSupporterCode() : null);
        dto.setLabName(r.getLab() != null ? r.getLab().getLabName() : null);
        dto.setRoomName(r.getRoom() != null ? r.getRoom().getRoomName() : null);
        dto.setTitle(r.getTitle());
        dto.setDescription(r.getDescription());
        dto.setStatus(r.getStatus() != null ? r.getStatus().name() : null);
        dto.setRequestedAt(r.getRequestedAt());
        dto.setApprovedAt(r.getApprovedAt());
        dto.setCompletedAt(r.getCompletedAt());

        dto.setRoomSlots(r.getRoomSlots() != null
                ? r.getRoomSlots().stream()
                .map(rs -> rs.getSlotName() + " (" + rs.getStartTime() + " - " + rs.getEndTime() + ") - " + rs.getBookingDate())
                .collect(Collectors.toList())
                : null);

        return dto;
    }
}

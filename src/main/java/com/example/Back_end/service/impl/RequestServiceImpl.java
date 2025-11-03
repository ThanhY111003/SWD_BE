package com.example.Back_end.service.impl;

import com.example.Back_end.dto.RequestRequestDTO;
import com.example.Back_end.dto.RequestResponseDTO;
import com.example.Back_end.entity.*;
import com.example.Back_end.enums.RequestStatus;
import com.example.Back_end.repository.*;
import com.example.Back_end.service.interf.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepo;
    private final RequestTypeRepository requestTypeRepo;
    private final MemberRepository memberRepo;
    private final StaffRepository staffRepo;
    private final LabRepository labRepo;
    private final RoomRepository roomRepo;
    private final RoomSlotRepository roomSlotRepo;
    private final SupporterRepository supporterRepo;
    private final SupporterShiftRepository supporterShiftRepo;

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

        request.setRequestType(requestTypeRepo.findById(dto.getRequestTypeId())
                .orElseThrow(() -> new RuntimeException("RequestType not found")));
        request.setMember(memberRepo.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found")));
        request.setLab(labRepo.findById(dto.getLabId())
                .orElseThrow(() -> new RuntimeException("Lab not found")));

        String typeName = request.getRequestType().getTypeName().toUpperCase();

        // ------------------------------------------------------------
        // CASE 1: BOOKING
        // ------------------------------------------------------------
        if ("BOOKING".equals(typeName) && dto.getRoomSlotIds() != null) {
            request.setRoomSlots(dto.getRoomSlotIds().stream()
                    .map(id -> roomSlotRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("RoomSlot not found")))
                    .collect(Collectors.toList()));

            // Gán staff phụ trách lab
            staffRepo.findFirstByLabId(request.getLab().getLabId()).orElse(null);

        }

        // ------------------------------------------------------------
        // CASE 2: OPEN_DOOR
        // ------------------------------------------------------------
        else if ("OPEN_DOOR".equals(typeName) && dto.getRoomSlotIds() != null && !dto.getRoomSlotIds().isEmpty()) {
            // Gán supporter phù hợp với ca làm việc (dựa vào slot)
            RoomSlot rs = roomSlotRepo.findById(dto.getRoomSlotIds().get(0))
                    .orElseThrow(() -> new RuntimeException("RoomSlot not found"));

            Supporter supporter = findAvailableSupporterForRoomSlot(rs);
            if (supporter != null) {
                request.setSupporter(supporter);
            }
        }

        return toResponse(requestRepo.save(request));
    }

    @Override
    public RequestResponseDTO update(Long id, RequestRequestDTO dto) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        RequestStatus newStatus = RequestStatus.valueOf(dto.getStatus().toUpperCase());
        request.setStatus(newStatus);

        String typeName = request.getRequestType().getTypeName().toUpperCase();

        // ------------------------------------------------------------
        // APPROVED
        // ------------------------------------------------------------
        if (newStatus == RequestStatus.APPROVED) {
            request.setApprovedAt(LocalDateTime.now());

            if ("BOOKING".equals(typeName)) {
                if (dto.getRoomId() != null) {
                    Room room = roomRepo.findById(dto.getRoomId())
                            .orElseThrow(() -> new RuntimeException("Room not found"));
                    request.setRoom(room);
                }
                staffRepo.findFirstByLabId(request.getLab().getLabId()).orElse(null);

            }

            else if ("OPEN_DOOR".equals(typeName) && dto.getRoomSlotIds() != null && !dto.getRoomSlotIds().isEmpty()) {
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
            request.setRoom(null);
            request.setSupporter(null);
        }

        // ------------------------------------------------------------
        // COMPLETED
        // ------------------------------------------------------------
        else if (newStatus == RequestStatus.COMPLETED) {
            request.setCompletedAt(LocalDateTime.now());
        }

        return toResponse(requestRepo.save(request));
    }

    @Override
    public void delete(Long id) {
        requestRepo.deleteById(id);
    }

    // =======================================================================
    //                           HELPER FUNCTIONS
    // =======================================================================

    /**
     * Tìm supporter có ca làm trùng thời gian với RoomSlot.
     */
    private Supporter findAvailableSupporterForRoomSlot(RoomSlot rs) {
        var shifts = supporterShiftRepo.findAvailableShiftsForSlot(
                rs.getBookingDate(),
                rs.getSlot().getStartTime(),
                rs.getSlot().getEndTime()
        );

        if (shifts.isEmpty()) return null;

        // Ưu tiên supporter đầu tiên trong ca
        var shift = shifts.get(0);
        if (shift.getSupporters() != null && !shift.getSupporters().isEmpty()) {
            return shift.getSupporters().get(0);
        }
        return null;
    }

    /**
     * Chuyển entity sang DTO trả về.
     */
    private RequestResponseDTO toResponse(Request r) {
        RequestResponseDTO dto = new RequestResponseDTO();
        dto.setRequestId(r.getRequestId());
        dto.setRequestType(r.getRequestType() != null ? r.getRequestType().getTypeName() : null);
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
                .map(rs -> rs.getSlot().getSlotName() + " - " + rs.getBookingDate())
                .collect(Collectors.toList())
                : null);
        return dto;
    }
}

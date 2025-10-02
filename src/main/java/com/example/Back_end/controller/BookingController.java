package com.example.Back_end.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking Controller", description = "Quản lý đặt lịch phòng thí nghiệm")
public class BookingController {

    @Operation(
            summary = "Lấy tất cả đặt lịch",
            description = "Trả về danh sách tất cả các đặt lịch trong hệ thống"
    )
    @GetMapping
    public Object getAllBookings() {
        // TODO: Call service to get all bookings
        return null;
    }

    @Operation(
            summary = "Lấy thông tin đặt lịch theo ID",
            description = "Trả về thông tin chi tiết của đặt lịch dựa trên ID"
    )
    @GetMapping("/{id}")
    public Object getBookingById(@PathVariable Integer id) {
        // TODO: Call service to get booking by id
        return null;
    }

    @Operation(
            summary = "Tạo mới đặt lịch",
            description = "Tạo một đặt lịch mới với thông tin được cung cấp"
    )
    @PostMapping
    public Object createBooking(@RequestBody Object bookingDto) {
        // TODO: Call service to create booking
        return null;
    }

    @Operation(
            summary = "Cập nhật đặt lịch theo ID",
            description = "Cập nhật thông tin đặt lịch dựa trên ID và dữ liệu mới"
    )
    @PutMapping("/{id}")
    public Object updateBooking(@PathVariable Integer id, @RequestBody Object bookingDto) {
        // TODO: Call service to update booking
        return null;
    }

    @Operation(
            summary = "Xóa đặt lịch theo ID",
            description = "Xóa đặt lịch khỏi hệ thống dựa trên ID"
    )
    @DeleteMapping("/{id}")
    public Object deleteBooking(@PathVariable Integer id) {
        // TODO: Call service to delete booking
        return null;
    }
}

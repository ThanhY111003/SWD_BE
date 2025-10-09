package com.example.Back_end.entity;

import com.example.Back_end.entity.entity_enum.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Schema(description = "Thông tin người dùng trong hệ thống")
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(description = "ID người dùng", example = "1")
    private Integer userId;

    @Schema(description = "Tên đăng nhập", example = "johndoe")
    @Column(name = "user_name", nullable = false, unique = true, length = 100)
    private String userName;

    @Schema(description = "Mật khẩu đã mã hoá", example = "$2a$10$...")
    @Column(nullable = false)
    private String password;

    @Schema(description = "Email của người dùng", example = "john@example.com")
    @Column(unique = true, length = 150)
    private String email;

    @Schema(description = "Số điện thoại", example = "0123456789")
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status; // import từ enums.UserStatus

    @Schema(description = "Thời điểm tạo tài khoản")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Schema(description = "Thời điểm cập nhật tài khoản")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Quan hệ với Role (chuẩn ERD: User N-1 Role)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    // Một user có thể tạo nhiều request
    @OneToMany(mappedBy = "createdBy")
    private List<Request> createdRequests;

    // Một user có thể duyệt nhiều request
    @OneToMany(mappedBy = "approvedBy")
    private List<Request> approvedRequests;

    // Một user có thể đặt nhiều booking
    @OneToMany(mappedBy = "bookedBy")
    private List<Booking> bookedBookings;

    // Một user có thể duyệt nhiều booking
    @OneToMany(mappedBy = "approvedBy")
    private List<Booking> approvedBookings;

    // Nhiều User quản lý nhiều Lab
    @ManyToMany
    @JoinTable(
            name = "user_lab",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lab_id")
    )
    private Set<Lab> managedLabs;


    // Một user có thể có nhiều shift
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupporterShift> supporterShifts;

}

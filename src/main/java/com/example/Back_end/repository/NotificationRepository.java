package com.example.Back_end.repository;

import com.example.Back_end.entity.Notification;
import com.example.Back_end.entity.NotificationType;
import com.example.Back_end.entity.entity_enum.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserUserId(Integer userId);

    List<Notification> findByStatus(NotificationStatus status);

    List<Notification> findByNotificationType(NotificationType notificationType);

}

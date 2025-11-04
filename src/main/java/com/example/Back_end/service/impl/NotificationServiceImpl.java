package com.example.Back_end.service.impl;

import com.example.Back_end.entity.Member;
import com.example.Back_end.entity.Notification;
import com.example.Back_end.entity.Staff;
import com.example.Back_end.entity.Supporter;
import com.example.Back_end.repository.NotificationRepository;
import com.example.Back_end.service.interf.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepo;

    @Override
    public void notifyMember(Member member, String title, String message) {
        if (member == null) return;
        Notification n = new Notification();
        n.setTitle(title);
        n.setMessage(message);
        n.setMember(member);
        notificationRepo.save(n);
    }

    @Override
    public void notifyStaff(Staff staff, String title, String message) {
        if (staff == null) return;
        Notification n = new Notification();
        n.setTitle(title);
        n.setMessage(message);
        n.setStaff(staff);
        notificationRepo.save(n);
    }

    @Override
    public void notifySupporter(Supporter supporter, String title, String message) {
        if (supporter == null) return;
        Notification n = new Notification();
        n.setTitle(title);
        n.setMessage(message);
        n.setSupporter(supporter);
        notificationRepo.save(n);
    }
}

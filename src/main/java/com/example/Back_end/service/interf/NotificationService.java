package com.example.Back_end.service.interf;

import com.example.Back_end.entity.Member;
import com.example.Back_end.entity.Staff;
import com.example.Back_end.entity.Supporter;

public interface NotificationService {
    void notifyMember(Member member, String title, String message);
    void notifyStaff(Staff staff, String title, String message);
    void notifySupporter(Supporter supporter, String title, String message);
}

package com.example.Back_end.repository;

import com.example.Back_end.entity.Request;
import com.example.Back_end.entity.entity_enum.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findByCreatedByUserId(Integer userId);

    List<Request> findByApprovedByUserId(Integer userId);

    List<Request> findByLabLabId(Integer labId);

    List<Request> findByStatus(RequestStatus status);

    List<Request> findByRequestTypeRequestTypeId(Integer typeId);
}

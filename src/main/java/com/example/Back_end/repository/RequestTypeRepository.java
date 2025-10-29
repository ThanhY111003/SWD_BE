package com.example.Back_end.repository;

import com.example.Back_end.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
    RequestType findByTypeName(String typeName);
}

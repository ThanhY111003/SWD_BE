package com.example.Back_end.repository;

import com.example.Back_end.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestTypeRepository extends JpaRepository<RequestType, Integer> {

    Optional<RequestType> findByTypeName(String typeName);
}

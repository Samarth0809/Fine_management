package com.centralrailway.finemanagement.repository;

import com.centralrailway.finemanagement.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByStatus(String status);
}
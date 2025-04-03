package com.centralrailway.finemanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralrailway.finemanagement.model.Fine;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByStatus(String status);
    List<Fine> findAllByIssueDateBetween(LocalDate startDate, LocalDate endDate);
}
package com.centralrailway.finemanagement.service;

import com.centralrailway.finemanagement.model.Fine;
import com.centralrailway.finemanagement.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
	
    @Autowired
    private FineRepository fineRepository;

    public List<Fine> getFinesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return fineRepository.findAllByIssueDateBetween(startDate, endDate);
    }

    public double getTotalFinesAmountBetweenDates(LocalDate startDate, LocalDate endDate) {
        return fineRepository.findAllByIssueDateBetween(startDate, endDate).stream()
                .mapToDouble(Fine::getAmount)
                .sum();
    }
}

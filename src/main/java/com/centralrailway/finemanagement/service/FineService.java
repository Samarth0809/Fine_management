package com.centralrailway.finemanagement.service;

import com.centralrailway.finemanagement.model.Fine;
import com.centralrailway.finemanagement.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FineService {
    @Autowired
    private FineRepository fineRepository;

    public Fine issueFine(String passengerName, String violation, Double amount) {
        Fine fine = new Fine();
        fine.setPassengerName(passengerName);
        fine.setViolation(violation);
        fine.setAmount(amount);
        fine.setIssueDate(LocalDate.now());
        fine.setStatus("ISSUED");
        return fineRepository.save(fine);
    }

    public List<Fine> getFinesByStatus(String status) {
        return fineRepository.findByStatus(status);
    }

    public Fine payFine(Long fineId) {
        Fine fine = fineRepository.findById(fineId).orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setStatus("PAID");
        return fineRepository.save(fine);
    }
}
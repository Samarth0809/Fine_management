package com.centralrailway.finemanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralrailway.finemanagement.model.Fine;
import com.centralrailway.finemanagement.repository.FineRepository;

@Service
public class FineService {
    @Autowired
    private FineRepository fineRepository;
    
    @Autowired
    private EmailService emailService;

    public Fine issueFine(String passengerName, String passengerEmail, String violation, Double amount) {
        Fine fine = new Fine();
        fine.setPassengerName(passengerName);
        fine.setViolation(violation);
        fine.setAmount(amount);
        fine.setIssueDate(LocalDate.now());
        fine.setStatus("ISSUED");
        
        // Save the fine first and store it in a variable
        Fine issuedFine = fineRepository.save(fine);

        // Send email notification
        String subject = "Fine Issued";
        String text = "Dear " + passengerName + ",\n\nYou have been issued a fine for the following violation: " + violation + 
                      ".\nAmount: ₹" + amount + "\nPlease pay your fine at your earliest convenience.\n\nThank you.";
        
        emailService.sendEmail(passengerEmail, subject, text);

        return issuedFine;  // Return the saved fine
    }

    public List<Fine> getFinesByStatus(String status) {
        return fineRepository.findByStatus(status);
    }

    public Fine payFine(Long fineId) {
        Fine fine = fineRepository.findById(fineId)
                                  .orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setStatus("PAID");
        return fineRepository.save(fine);
    }
}

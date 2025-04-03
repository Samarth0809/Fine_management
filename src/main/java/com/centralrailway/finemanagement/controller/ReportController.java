package com.centralrailway.finemanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.centralrailway.finemanagement.model.Fine;
import com.centralrailway.finemanagement.service.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/fines")
    public String getFinesReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        List<Fine> fines = reportService.getFinesBetweenDates(startDate, endDate);
        double totalAmount = reportService.getTotalFinesAmountBetweenDates(startDate, endDate);
        model.addAttribute("fines", fines);
        model.addAttribute("totalAmount", totalAmount);
        return "fines-report";
    }
}
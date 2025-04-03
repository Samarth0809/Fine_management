package com.centralrailway.finemanagement.controller;

import com.centralrailway.finemanagement.model.Fine;
import com.centralrailway.finemanagement.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fines")
public class FineController {
    @Autowired
    private FineService fineService;

    @GetMapping("/issue")
    public String showIssueFineForm(Model model) {
        model.addAttribute("fine", new Fine());
        return "issue-fine";
    }

    @PostMapping("/issue")
    public String issueFine(@ModelAttribute Fine fine, @RequestParam("email") String email) {
        fineService.issueFine(fine.getPassengerName(), email, fine.getViolation(), fine.getAmount());
        return "redirect:/fines/list";
    }

    @GetMapping("/list")
    public String listFines(Model model) {
        List<Fine> fines = fineService.getFinesByStatus("ISSUED");
        model.addAttribute("fines", fines);
        return "list-fines";
    }

    @PostMapping("/pay/{id}")
    public String payFine(@PathVariable Long id) {
        fineService.payFine(id);
        return "redirect:/fines/list";
    }
}
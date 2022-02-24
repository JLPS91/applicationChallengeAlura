package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.MonthlySummaryDTO;
import com.projects.challenge.alura.service.MonthlySummaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget/control")
@AllArgsConstructor
public class MonthlySummaryController {

    private MonthlySummaryService monthlySummaryService;

    @GetMapping(path = "/summary/{year}/{month}")
    public MonthlySummaryDTO getFinalBalance(@PathVariable Integer year, @PathVariable Integer month) {
        return monthlySummaryService.getFinalBalance(year, month);
    }
}

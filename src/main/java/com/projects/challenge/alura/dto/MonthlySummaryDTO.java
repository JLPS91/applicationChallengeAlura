package com.projects.challenge.alura.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlySummaryDTO {

    private BigDecimal totalAmountIncomes;
    private BigDecimal totalAmountExpenses;
    private BigDecimal finalBalance;

    public MonthlySummaryDTO(BigDecimal totalAmountIncomes, BigDecimal totalAmountExpenses, BigDecimal finalBalance) {
        this.totalAmountIncomes = totalAmountIncomes;
        this.totalAmountExpenses = totalAmountExpenses;
        this.finalBalance = finalBalance;
    }
}

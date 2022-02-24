package com.projects.challenge.alura.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MonthlySummaryDTO {

    private BigDecimal totalAmountIncomes;
    private BigDecimal totalAmountExpenses;
    private BigDecimal finalBalance;
    private List<ExpensesByCategoryDTO> expensesByCategory;

    public MonthlySummaryDTO(BigDecimal totalAmountIncomes, BigDecimal totalAmountExpenses, BigDecimal finalBalance,
                             List<ExpensesByCategoryDTO> expensesByCategory) {
        this.totalAmountIncomes = totalAmountIncomes;
        this.totalAmountExpenses = totalAmountExpenses;
        this.finalBalance = finalBalance;
        this.expensesByCategory = expensesByCategory;
    }
}

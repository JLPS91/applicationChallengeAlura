package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.ExpensesByCategoryDTO;
import com.projects.challenge.alura.dto.MonthlySummaryDTO;
import com.projects.challenge.alura.repository.ExpensesRepository;
import com.projects.challenge.alura.repository.IncomesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class MonthlySummaryService {

    private IncomesRepository incomesRepository;
    private ExpensesRepository expensesRepository;

    public MonthlySummaryDTO getFinalBalance(Integer year, Integer month) {
        BigDecimal totalAmountIncomes = incomesRepository.totalIncomeInTheMonth(year, month).orElse(BigDecimal.ZERO);
        BigDecimal totalAmountExpenses = expensesRepository.totalExpensesInTheMonth(year, month).orElse(BigDecimal.ZERO);
        BigDecimal finalBalance = totalAmountIncomes.subtract(totalAmountExpenses);
        List<ExpensesByCategoryDTO> expensesByCategory = expensesRepository.valueGroupedByCategory(year, month);
        return new MonthlySummaryDTO(totalAmountIncomes, totalAmountExpenses, finalBalance, expensesByCategory);
    }
}

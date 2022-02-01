package com.projects.challenge.alura.repository;

import com.projects.challenge.alura.dto.ExpensesByCategoryDTO;
import com.projects.challenge.alura.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    @Query("select e from Expenses e where year(e.date) = :year and month(e.date) = :month")
    List<Expenses> listYearAndMonth(Integer year, Integer month);

    @Query("select sum(e.amount) from Expenses e where year(e.date) = :year and month(e.date) = :month")
    Optional<BigDecimal> totalExpensesInTheMonth(Integer year, Integer month);

    @Query("select new com.projects.challenge.alura.dto.ExpensesByCategoryDTO(e.category, sum(e.amount)) from Expenses e " +
            " where YEAR(e.date) = :year and MONTH(e.date) = :month " +
            " group by e.category")
    List<ExpensesByCategoryDTO> valueGroupedByCategory(Integer year, Integer month);
}

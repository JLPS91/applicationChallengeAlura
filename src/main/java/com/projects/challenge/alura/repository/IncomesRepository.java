package com.projects.challenge.alura.repository;

import com.projects.challenge.alura.entity.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface IncomesRepository extends JpaRepository<Incomes, Long> {

    @Query("select e from Incomes e where year(e.date) = :year and month(e.date) = :month")
    List<Incomes> listYearAndMonth(Integer year, Integer month);

    @Query("select sum(e.amount) from Incomes e where year(e.date) = :year and month(e.date) = :month")
    BigDecimal totalIncomeInTheMonth(Integer year, Integer month);
}

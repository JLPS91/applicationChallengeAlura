package com.projects.challenge.alura.repository;

import com.projects.challenge.alura.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

    @Query("select e from Expenses e where year(e.date) = :year and month(e.date) = :month")
    List<Expenses> listYearAndMonth(Integer year, Integer month);
}

package com.projects.challenge.alura.repository;

import com.projects.challenge.alura.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    List<Expenses> findAllById(Long id);
}

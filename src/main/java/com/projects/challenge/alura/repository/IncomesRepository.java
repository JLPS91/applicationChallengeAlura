package com.projects.challenge.alura.repository;

import com.projects.challenge.alura.entity.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomesRepository extends JpaRepository<Incomes, Long> {
    List<Incomes> findAllById(Long id);
}

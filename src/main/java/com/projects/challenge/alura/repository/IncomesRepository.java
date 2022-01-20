package com.projects.challenge.alura.repository;

import com.projects.challenge.alura.entity.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomesRepository extends JpaRepository<Incomes, Long> {
}

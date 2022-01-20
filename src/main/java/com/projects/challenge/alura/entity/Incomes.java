package com.projects.challenge.alura.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Incomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate date;
}

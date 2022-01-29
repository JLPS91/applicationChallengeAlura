package com.projects.challenge.alura.dto;

import com.projects.challenge.alura.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class ExpensesDTO {

    private Long id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String amount;

    @NotEmpty
    private String date;

    @Enumerated(EnumType.STRING)
    private Category category;
}

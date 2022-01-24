package com.projects.challenge.alura.dto;

public class ExpensesDTO extends RequestDTO {

    public ExpensesDTO(Long id, String description, String amount, String date) {
        super(id, description, amount, date);
    }
}

package com.projects.challenge.alura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpensesNotFoundException extends Exception {

    public ExpensesNotFoundException(Long id) {
        super("Expenses not found with ID " + id);
    }
}

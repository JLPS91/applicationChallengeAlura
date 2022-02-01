package com.projects.challenge.alura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncomesNotFoundException extends Exception {

    public IncomesNotFoundException(Long id) {
        super("Incomes not found with ID " + id);
    }
}

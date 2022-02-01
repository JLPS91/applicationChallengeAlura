package com.projects.challenge.alura.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorFormDTO {

    private String field;
    private String errorMessage;
}

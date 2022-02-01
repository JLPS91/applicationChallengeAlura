package com.projects.challenge.alura.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

//Esta classe simplifica o json de retorno passando uma msg personalizada
@RestControllerAdvice
public class ErrorValidationHandler {

    //Traz uma msg personalizada de acordo com idioma conf. no retorno
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public List<ErrorFormDTO> handle(MethodArgumentNotValidException exception) {
        List<ErrorFormDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorFormDTO error = new ErrorFormDTO(e.getField(), message);
            dto.add(error);
        });

        return dto;
    }
}

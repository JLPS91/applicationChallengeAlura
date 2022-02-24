package com.projects.challenge.alura.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class IncomesDTO {

    private Long id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String amount;

    @NotEmpty
    private String date;
}

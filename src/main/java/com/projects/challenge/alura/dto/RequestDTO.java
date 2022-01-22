package com.projects.challenge.alura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RequestDTO {

    private Long id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String amount;

    @NotEmpty
    private String date;
}

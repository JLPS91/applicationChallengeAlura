package com.projects.challenge.alura.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private String message;


    public static MessageResponseDTO createMessageResponseDTO(String message) {
        return MessageResponseDTO
                .builder()
                .message(message)
                .build();
    }

}

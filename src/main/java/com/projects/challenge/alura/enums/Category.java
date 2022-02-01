package com.projects.challenge.alura.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

    FOOD("Food"),

    HEALTH("Health"),

    HOME("Home"),

    TRANSPORT("Transport"),

    EDUCATION("Education"),

    LEISURE("Leisure"),

    UNFORESEEN_EXPENSES("Unforeseen expenses"),

    OTHERS("Others");

    private final String description;
}

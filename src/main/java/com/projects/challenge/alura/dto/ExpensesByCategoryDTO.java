package com.projects.challenge.alura.dto;

import com.projects.challenge.alura.enums.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpensesByCategoryDTO {

    private Category category;
    private Double totalAmount;


    public ExpensesByCategoryDTO(Category category, Double totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategoria(Category category) {
        this.category = category;
    }
}

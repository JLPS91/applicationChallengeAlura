package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
import com.projects.challenge.alura.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesService {

    private ExpensesRepository expensesRepository;

    @Autowired
    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public MessageResponseDTO createExpenses(Expenses expenses) {

        Expenses savedExpense = expensesRepository.save(expenses);
        return MessageResponseDTO
                .builder()
                .message("Successfully created, ID = " + savedExpense.getId())
                .build();
    }

    public List<Expenses> listAll() {
        return expensesRepository.findAll();
    }

    public List<Expenses> findById(Long id) {
        return expensesRepository.findAllById(id);
    }
}

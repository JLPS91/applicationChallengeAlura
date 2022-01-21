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
        return createMessageResponseDTO("Successfully created, ID = ", savedExpense.getId());
    }

    public List<Expenses> listAll() {
        return expensesRepository.findAll();
    }

    public List<Expenses> findById(Long id) {
        return getAllById(id);
    }

    public void delete(Long id) {
        getAllById(id);
        expensesRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, Expenses expenses) {

        getAllById(id);
        Expenses updatedExpenses = expensesRepository.save(expenses);
        return createMessageResponseDTO("Successfully updated, ID = ", updatedExpenses.getId());
    }

    private MessageResponseDTO createMessageResponseDTO(String message, Long id) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private List<Expenses> getAllById(Long id) {
        return expensesRepository.findAllById(id);
    }
}

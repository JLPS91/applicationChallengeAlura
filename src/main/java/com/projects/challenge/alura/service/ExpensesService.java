package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
import com.projects.challenge.alura.repository.ExpensesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpensesService {

    private ExpensesRepository expensesRepository;

    public MessageResponseDTO createExpenses(Expenses expenses) {
        Expenses savedExpense = expensesRepository.save(expenses);
        return MessageResponseDTO.createMessageResponseDTO("Successfully created, ID = ", savedExpense.getId());
    }

    public List<Expenses> listAll() {
        return expensesRepository.findAll();
    }

    public List<Expenses> findById(Long id) {
        return getAllById(id);
    }

    public MessageResponseDTO updateById(Long id, Expenses expenses) {
        getAllById(id);
        Expenses updatedExpenses = expensesRepository.save(expenses);
        return MessageResponseDTO.createMessageResponseDTO("Successfully updated, ID = ", updatedExpenses.getId());
    }

    public void delete(Long id) {
        getAllById(id);
        expensesRepository.deleteById(id);
    }

    private List<Expenses> getAllById(Long id) {
        return expensesRepository.findAllById(id);
    }
}

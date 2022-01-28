package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.ExpensesDTO;
import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
import com.projects.challenge.alura.exception.ExpensesNotFoundException;
import com.projects.challenge.alura.mapper.ExpensesMapper;
import com.projects.challenge.alura.repository.ExpensesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpensesService {

    private ExpensesRepository expensesRepository;

    private final ExpensesMapper expensesMapper = ExpensesMapper.INSTANCE;

    public MessageResponseDTO createExpenses(ExpensesDTO expensesDTO) {
        Expenses expensesToSave = expensesMapper.toModel(expensesDTO);
        Expenses savedExpense = expensesRepository.save(expensesToSave);

        if (validDescription(expensesToSave)) {
            return MessageResponseDTO.createMessageResponseDTO
                    ("This description has already been registered for this month, ID = ", savedExpense.getId());
        }
        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully created, ID = ", savedExpense.getId());
    }

    public List<ExpensesDTO> listAll() {
        List<Expenses> allExpenses = expensesRepository.findAll();
        return allExpenses.stream()
                .map(expensesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ExpensesDTO findById(Long id) throws ExpensesNotFoundException {
        Expenses expenses = getByID(id);
        return expensesMapper.toDTO(expenses);
    }

    public MessageResponseDTO updateById(Long id, ExpensesDTO expensesDTO) throws ExpensesNotFoundException {
        getByID(id);
        Expenses expensesToSave = expensesMapper.toModel(expensesDTO);
        Expenses updatedExpenses = expensesRepository.save(expensesToSave);
        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully updated, ID = ", updatedExpenses.getId());
    }

    public void delete(Long id) throws ExpensesNotFoundException {
        getByID(id);
        expensesRepository.deleteById(id);
    }

    private Expenses getByID(Long id) throws ExpensesNotFoundException {
        return expensesRepository.findById(id)
                .orElseThrow(() -> new ExpensesNotFoundException(id));
    }

    private boolean validDescription(Expenses expenses) {

        List<Expenses> q1 = expensesRepository.findAll();

        for (Expenses value : q1) {
            if (expenses.getDate().getMonthValue() == value.getDate().getMonthValue()) {
                if (expenses.getDescription().equals(value.getDescription())) {
                    return true;

                }

            }
        }

        return false;
    }
}

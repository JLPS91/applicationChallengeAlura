package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.ExpensesDTO;
import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
import com.projects.challenge.alura.enums.Category;
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
        if (validDescriptionDuplicate(expensesToSave)) {
            return MessageResponseDTO.createMessageResponseDTO
                    ("Duplicate record for current month: change description!");
        }
        validCategory(expensesToSave);
        expensesRepository.save(expensesToSave);

        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully created!");
    }

    public List<ExpensesDTO> listAll() {
        List<Expenses> allExpenses = expensesRepository.findAll();
        return allExpenses.stream()
                .map(expensesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ExpensesDTO> listByYearAndMonth(Integer year, Integer month) {

        List<Expenses> listYearAndMonth = expensesRepository.listYearAndMonth(year, month);
        return listYearAndMonth.stream()
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
        expensesRepository.save(expensesToSave);
        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully updated!");
    }

    public void delete(Long id) throws ExpensesNotFoundException {
        getByID(id);
        expensesRepository.deleteById(id);
    }

    private Expenses getByID(Long id) throws ExpensesNotFoundException {
        return expensesRepository.findById(id)
                .orElseThrow(() -> new ExpensesNotFoundException(id));
    }

    private boolean validDescriptionDuplicate(Expenses expenses) {

        List<Expenses> list = expensesRepository.findAll();

        for (Expenses value : list) {
            if (expenses.getDate().getMonthValue() == value.getDate().getMonthValue()) {
                if (expenses.getDescription().equals(value.getDescription())) {
                    return true;
                }

            }
        }
        return false;
    }

    private void validCategory(Expenses expensesToSave) {
        if (expensesToSave.getCategory() == null || expensesToSave.getCategory().toString().isEmpty()) {
            expensesToSave.setCategory(Category.OTHERS);
        }
    }
}

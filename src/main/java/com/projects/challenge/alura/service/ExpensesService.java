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

import java.util.ArrayList;
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
                    ("Registro duplicado para o mês atual: Altere descrição!");
        }
        validCategory(expensesToSave);
        expensesRepository.save(expensesToSave);

        return MessageResponseDTO.createMessageResponseDTO
                ("Criado com sucesso!");
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

    public List<ExpensesDTO> listByDescription(String description) {
        return getListDescription(description);
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
                ("Atualizado com sucesso!");
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

    private List<ExpensesDTO> getListDescription(String description) {
        List<ExpensesDTO> listDescriptionDTO = new ArrayList<>();
        List<Expenses> listDescription = expensesRepository.findAll();

        for (Expenses expenses : listDescription) {
            if (expenses.getDescription().toLowerCase().contains(description.toLowerCase())) {
                ExpensesDTO expensesDTO = new ExpensesDTO();
                expensesDTO.setDescription(expenses.getDescription());
                expensesDTO.setAmount(expenses.getAmount().toString());
                expensesDTO.setDate(expenses.getDate().toString());
                expensesDTO.setId(expenses.getId());
                expensesDTO.setCategory(expenses.getCategory());

                listDescriptionDTO.add(expensesDTO);
            }

        }

        return listDescriptionDTO;
    }

    private void validCategory(Expenses expenses) {
        if (expenses.getCategory() == null || expenses.getCategory().toString().isEmpty()) {
            expenses.setCategory(Category.OTHERS);
        }
    }
}

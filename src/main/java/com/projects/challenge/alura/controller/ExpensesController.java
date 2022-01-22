package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.ExpensesDTO;
import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
import com.projects.challenge.alura.exception.ExpensesNotFoundException;
import com.projects.challenge.alura.service.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


@RestController
@RequestMapping("/budget/control")
@AllArgsConstructor
public class ExpensesController {

    private ExpensesService expensesService;

    @PostMapping(path = "/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createExpenses(@RequestBody @Valid ExpensesDTO expensesDTO) {
        return expensesService.createExpenses(expensesDTO);
    }

    @GetMapping(path = "/expenses")
    public List<ExpensesDTO> listAll() {
        return expensesService.listAll();
    }

    @GetMapping(path = "/expenses/{id}")
    public ExpensesDTO findById(@PathVariable Long id) throws ExpensesNotFoundException {
        return expensesService.findById(id);

    }

    @PutMapping(path = "/expenses/update/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ExpensesDTO expenseDTO) throws ExpensesNotFoundException {
        return expensesService.updateById(id, expenseDTO);
    }

    @DeleteMapping(path = "/expenses/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ExpensesNotFoundException {
        expensesService.delete(id);
    }
}

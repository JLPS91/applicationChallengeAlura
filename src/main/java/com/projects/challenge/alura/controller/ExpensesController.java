package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
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
    public MessageResponseDTO createExpenses(@RequestBody @Valid Expenses expenses) {
        return expensesService.createExpenses(expenses);
    }

    @GetMapping(path = "/expenses")
    public List<Expenses> listAll() {
        return expensesService.listAll();
    }

    @GetMapping(path = "/expenses/{id}")
    public List<Expenses> findById(@PathVariable Long id) {
        return expensesService.findById(id);

    }

    @PutMapping(path = "/expenses/update")
    public MessageResponseDTO updateById(Long id, @RequestBody @Valid Expenses expense) {
        return expensesService.updateById(id, expense);
    }

    @DeleteMapping(path = "/expenses/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        expensesService.delete(id);
    }
}

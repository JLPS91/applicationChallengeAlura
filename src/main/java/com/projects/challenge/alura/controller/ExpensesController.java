package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Expenses;
import com.projects.challenge.alura.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/budget/control")
public class ExpensesController {

    private ExpensesService expensesService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping(path = "/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createExpenses(@RequestBody @Valid Expenses expenses) {
        return expensesService.createExpenses(expenses);
    }

    @GetMapping(path = "/listAllExpenses")
    public List<Expenses> listAll(){
        return expensesService.listAll();
    }

    @GetMapping(path = "/{id}")
    public List<Expenses> findById(@PathVariable Long id){
        return expensesService.findById(id);
        
    }

    @PutMapping(path = "/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid Expenses expense){
        return expensesService.updateById(id, expense);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        expensesService.delete(id);
    }
}

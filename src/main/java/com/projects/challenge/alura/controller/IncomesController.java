package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Incomes;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/budget/control")
@AllArgsConstructor
public class IncomesController {

    private IncomesService incomesService;

    @PostMapping(path = "/incomes")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createIncomes(@RequestBody @Valid Incomes incomes) {
        return incomesService.createIncomes(incomes);
    }

    @GetMapping(path = "/listAllIncomes")
    public List<Incomes> listAll() {
        return incomesService.listAll();
    }

    @GetMapping(path = "/incomesById/{id}")
    public List<Incomes> listById(@PathVariable Long id) {
        return incomesService.listById(id);
    }

    @PutMapping(path = "/update/incomes")
    public MessageResponseDTO updateIncomes(Long id, @RequestBody @Valid Incomes incomes) {
        return incomesService.updateIncomes(id, incomes);
    }

    @DeleteMapping(path = "/delete/incomes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       incomesService.delete(id);
    }

}

package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.IncomesDTO;
import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.exception.IncomesNotFoundException;
import com.projects.challenge.alura.service.IncomesService;
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
    public MessageResponseDTO createIncomes(@RequestBody @Valid IncomesDTO incomesDTO) {
    return incomesService.createIncomes(incomesDTO);
    }

    @GetMapping(path = "/incomes")
    public List<IncomesDTO> listAll() {
        return incomesService.listAll();
    }

    @GetMapping(path = "/incomes/{id}")
    public IncomesDTO listById(@PathVariable Long id) throws IncomesNotFoundException {
        return incomesService.listById(id);
    }

    @PutMapping(path = "/incomes/update/{id}")
    public MessageResponseDTO updateIncomes(@PathVariable Long id, @RequestBody @Valid IncomesDTO incomesDTO) throws IncomesNotFoundException {
        return incomesService.updateIncomes(id, incomesDTO);
    }

    @DeleteMapping(path = "/incomes/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IncomesNotFoundException {
       incomesService.delete(id);
    }

}

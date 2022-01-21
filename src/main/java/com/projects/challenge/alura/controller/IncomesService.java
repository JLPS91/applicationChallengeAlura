package com.projects.challenge.alura.controller;

import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Incomes;
import com.projects.challenge.alura.repository.IncomesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncomesService {

    private IncomesRepository incomesRepository;

    public MessageResponseDTO createIncomes(Incomes incomes) {
        Incomes savedIncomes = incomesRepository.save(incomes);
        return MessageResponseDTO.createMessageResponseDTO("Successfully created, ID = ", savedIncomes.getId());
    }

    public List<Incomes> listAll() {
        return incomesRepository.findAll();
    }

    public List<Incomes> listById(Long id) {
        return getAllById(id);

    }

    private List<Incomes> getAllById(Long id) {
        return incomesRepository.findAllById(id);
    }

    public MessageResponseDTO updateIncomes(Long id, Incomes incomes) {
        getAllById(id);
        Incomes updatedIncomes = incomesRepository.save(incomes);
        return MessageResponseDTO.createMessageResponseDTO("Successfully updated, ID = ", updatedIncomes.getId());
    }

    public void delete(Long id) {
        getAllById(id);
        incomesRepository.deleteById(id);

    }
}

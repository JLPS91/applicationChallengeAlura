package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.IncomesDTO;
import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Incomes;
import com.projects.challenge.alura.exception.IncomesNotFoundException;
import com.projects.challenge.alura.mapper.IncomesMapper;
import com.projects.challenge.alura.repository.IncomesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncomesService {

    private IncomesRepository incomesRepository;

    private final IncomesMapper incomesMapper = IncomesMapper.INSTANCE;

    public MessageResponseDTO createIncomes(IncomesDTO incomesDTO) {
        Incomes incomesToSave = incomesMapper.toModel(incomesDTO);
        Incomes savedIncomes = incomesRepository.save(incomesToSave);
        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully created, ID = ", savedIncomes.getId());
    }

    public List<IncomesDTO> listAll() {
        List<Incomes> allIncomes = incomesRepository.findAll();
        return allIncomes.stream()
                .map(incomesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IncomesDTO listById(Long id) throws IncomesNotFoundException {
        Incomes incomes = getByID(id);
        return incomesMapper.toDTO(incomes);
    }

    public MessageResponseDTO updateIncomes(Long id, IncomesDTO incomesDTO) throws IncomesNotFoundException {
        getByID(id);
        Incomes incomesToSave = incomesMapper.toModel(incomesDTO);
        Incomes updatedIncomes = incomesRepository.save(incomesToSave);
        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully updated, ID = ", updatedIncomes.getId());
    }

    public void delete(Long id) throws IncomesNotFoundException {
        getByID(id);
        incomesRepository.deleteById(id);
    }

    private Incomes getByID(Long id) throws IncomesNotFoundException {
        return incomesRepository.findById(id)
                .orElseThrow(() -> new IncomesNotFoundException(id));
    }

}

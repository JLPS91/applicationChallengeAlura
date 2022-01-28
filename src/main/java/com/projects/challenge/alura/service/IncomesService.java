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
        if (validDescriptionDuplicate(incomesToSave)) {
            return MessageResponseDTO.createMessageResponseDTO
                    ("Duplicate record for current month: change description!");
        }
        incomesRepository.save(incomesToSave);

        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully created!");
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
        incomesRepository.save(incomesToSave);
        return MessageResponseDTO.createMessageResponseDTO
                ("Successfully updated!");
    }

    public void delete(Long id) throws IncomesNotFoundException {
        getByID(id);
        incomesRepository.deleteById(id);
    }

    private Incomes getByID(Long id) throws IncomesNotFoundException {
        return incomesRepository.findById(id)
                .orElseThrow(() -> new IncomesNotFoundException(id));
    }

    private boolean validDescriptionDuplicate(Incomes incomes) {

        List<Incomes> q1 = incomesRepository.findAll();

        for (Incomes value : q1) {
            if (incomes.getDate().getMonthValue() == value.getDate().getMonthValue()) {
                if (incomes.getDescription().equals(value.getDescription())) {
                    return true;
                }

            }
        }
        return false;
    }
}

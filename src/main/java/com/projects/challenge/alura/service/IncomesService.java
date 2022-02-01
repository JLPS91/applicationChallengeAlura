package com.projects.challenge.alura.service;

import com.projects.challenge.alura.dto.IncomesDTO;
import com.projects.challenge.alura.dto.MessageResponseDTO;
import com.projects.challenge.alura.entity.Incomes;
import com.projects.challenge.alura.exception.IncomesNotFoundException;
import com.projects.challenge.alura.mapper.IncomesMapper;
import com.projects.challenge.alura.repository.IncomesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    ("Registro duplicado para o mês atual: Altere descrição!");
        }
        incomesRepository.save(incomesToSave);

        return MessageResponseDTO.createMessageResponseDTO
                ("Criado com sucesso!");
    }

    public List<IncomesDTO> listAll() {

        List<Incomes> allIncomes = incomesRepository.findAll();
        return allIncomes.stream()
                .map(incomesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<IncomesDTO> listByYearAndMonth(Integer year, Integer month) {
        List<Incomes> listYearAndMonth = incomesRepository.listYearAndMonth(year, month);
        return listYearAndMonth.stream()
                .map(incomesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<IncomesDTO> listByDescription(String description) {
        return getListDescription(description);
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
                ("Atualizado com sucesso!");
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

        List<Incomes> list = incomesRepository.findAll();

        for (Incomes value : list) {
            if (incomes.getDate().getMonthValue() == value.getDate().getMonthValue()) {
                if (incomes.getDescription().equals(value.getDescription())) {
                    return true;
                }

            }
        }
        return false;
    }

    private List<IncomesDTO> getListDescription(String description) {
        List<IncomesDTO> listDescriptionDTO = new ArrayList<>();
        List<Incomes> listDescription = incomesRepository.findAll();

        for (var q0 : listDescription) {
            if (q0.getDescription().toLowerCase().contains(description.toLowerCase())) {
                IncomesDTO incomesDTO = new IncomesDTO();
                incomesDTO.setDescription(q0.getDescription());
                incomesDTO.setAmount(q0.getAmount().toString());
                incomesDTO.setDate(q0.getDate().toString());
                incomesDTO.setId(q0.getId());

                listDescriptionDTO.add(incomesDTO);
            }

        }

        return listDescriptionDTO;
    }


}

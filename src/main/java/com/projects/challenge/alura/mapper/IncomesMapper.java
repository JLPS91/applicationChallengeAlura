package com.projects.challenge.alura.mapper;

import com.projects.challenge.alura.dto.IncomesDTO;
import com.projects.challenge.alura.entity.Incomes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncomesMapper {

    IncomesMapper INSTANCE = Mappers.getMapper(IncomesMapper.class);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy")
    Incomes toModel(IncomesDTO incomesDTO);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy")
    IncomesDTO toDTO(Incomes incomes);
}

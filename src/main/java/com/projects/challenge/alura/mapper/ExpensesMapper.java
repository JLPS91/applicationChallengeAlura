package com.projects.challenge.alura.mapper;

import com.projects.challenge.alura.dto.ExpensesDTO;
import com.projects.challenge.alura.entity.Expenses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpensesMapper {

    ExpensesMapper INSTANCE = Mappers.getMapper(ExpensesMapper.class);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy")
    Expenses toModel(ExpensesDTO expensesDTO);

    @Mapping(target = "date", dateFormat = "dd-MM-yyyy")
    ExpensesDTO toDTO(Expenses expenses);
}

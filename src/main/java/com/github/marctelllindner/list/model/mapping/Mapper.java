package com.github.marctelllindner.list.model.mapping;

public interface Mapper<ENTITY, DTO> {
    DTO entityToDto(ENTITY entity, String identifier);
    ENTITY dtoToEntity(DTO dto);
}

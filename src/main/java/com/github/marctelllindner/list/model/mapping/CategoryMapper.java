package com.github.marctelllindner.list.model.mapping;

import com.github.marctelllindner.list.model.dtos.CategoryDTO;
import com.github.marctelllindner.list.model.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

@Component
@AllArgsConstructor
public class CategoryMapper implements Mapper<CategoryEntity, CategoryDTO> {

    private final ItemMapper itemMapper;

    @Override
    public CategoryDTO entityToDto(final CategoryEntity categoryEntity, final String identifier) {
        return CategoryDTO.builder()
                .identifier(identifier)
                .name(categoryEntity.getName())
                .symbol(categoryEntity.getSymbol())
                .items(
                        categoryEntity.getItems() != null
                                ? categoryEntity.getItems().stream()
                                .map(
                                        item -> itemMapper.entityToDto(item, identifier)
                                )
                                .collect(toUnmodifiableList())
                                : Collections.emptyList()
                )
                .build();
    }

    @Override
    public CategoryEntity dtoToEntity(final CategoryDTO categoryDTO) {
        return new CategoryEntity()
                .setName(categoryDTO.getName())
                .setSymbol(categoryDTO.getSymbol())
                .setItems(
                        categoryDTO.getItems().stream()
                                .map(itemMapper::dtoToEntity)
                                .collect(toList())
                );
    }
}

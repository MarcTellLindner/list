package com.github.marctelllindner.list.model.mapping;

import com.github.marctelllindner.list.model.dtos.CategoryDTO;
import com.github.marctelllindner.list.model.dtos.ListDTO;
import com.github.marctelllindner.list.model.entities.CategoryEntity;
import com.github.marctelllindner.list.model.entities.ListEntity;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

@Component
@AllArgsConstructor
public class ListMapper implements Mapper<ListEntity, ListDTO> {
    private final CategoryMapper categoryMapper;

    @Override
    public ListDTO entityToDto(final ListEntity listEntity, final String identifier) {
        final List<CategoryDTO> categoryDTOs = listEntity.getCategories() != null
                ? listEntity.getCategories().stream()
                .map(
                        c -> categoryMapper.entityToDto(c, identifier)
                )
                .collect(toUnmodifiableList())
                : Collections.emptyList();

        return ListDTO.builder()
                .identifier(identifier)
                .name(listEntity.getName())
                .categories(categoryDTOs)
                .build();
    }

    @Override
    public ListEntity dtoToEntity(final ListDTO listDTO) {
        final List<CategoryEntity> categoryEntities = listDTO.getCategories() != null
                ? listDTO.getCategories().stream()
                .map(categoryMapper::dtoToEntity)
                .collect(toList())
                : Collections.emptyList();
        val itemEntities = categoryEntities.stream()
                .flatMap(c -> c.getItems().stream())
                .collect(toList());

        return new ListEntity()
                .setName(listDTO.getName())
                .setIdentifier(listDTO.getIdentifier())
                .setCategories(categoryEntities)
                .setItems(itemEntities);
    }
}

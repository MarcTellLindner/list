package com.github.marctelllindner.list.service;

import com.github.marctelllindner.list.model.crud.ListRepository;
import com.github.marctelllindner.list.model.dtos.CategoryDTO;
import com.github.marctelllindner.list.model.dtos.ItemDTO;
import com.github.marctelllindner.list.model.dtos.ListDTO;
import com.github.marctelllindner.list.model.entities.CategoryEntity;
import com.github.marctelllindner.list.model.entities.ItemEntity;
import com.github.marctelllindner.list.model.entities.ListEntity;
import com.github.marctelllindner.list.model.mapping.CategoryMapper;
import com.github.marctelllindner.list.model.mapping.ItemMapper;
import com.github.marctelllindner.list.model.mapping.ListMapper;
import com.github.marctelllindner.list.util.ErrorCodeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class ListService {
    private final ListRepository listRepository;

    private final ListMapper listMapper;
    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;

    public ListDTO createList(final String name) {
        val shoppingList = new ListEntity()
                .setName(name)
                .setIdentifier(generateIdentifier());
        val saved = listRepository.saveAndFlush(shoppingList);
        val dto = listMapper.entityToDto(saved, saved.getIdentifier());
        log.debug("Created: {}", dto);
        return dto;
    }

    public ListDTO getList(final String identifier) throws ErrorCodeException {
        val optional = listRepository.findByIdentifier(identifier);
        if (optional.isPresent()) {
            val found = optional.get();
            val dto = listMapper.entityToDto(found, found.getIdentifier());
            log.debug("Requested: {}", dto);
            return dto;
        }
        throw new ErrorCodeException(NOT_FOUND, "No list with this id");
    }

    @Transactional
    public ListDTO addItems(final String identifier, final List<CategoryDTO> items) throws ErrorCodeException {
        val optional = listRepository.findByIdentifier(identifier);
        if (optional.isEmpty()) {
            throw new ErrorCodeException(NOT_FOUND, "No list with this id");
        }
        val list = optional.get();

        for (final CategoryDTO categoryDTO : items) {
            insertCategory(list, categoryDTO);
        }

        val saved = listRepository.saveAndFlush(list);
        val dto = listMapper.entityToDto(saved, saved.getIdentifier());

        log.debug("Updated list: {}", dto);

        return dto;
    }

    private void insertCategory(final ListEntity list, final CategoryDTO categoryDTO) {
        if (list.getCategories() == null) {
            list.setCategories(new ArrayList<>());
        }
        Optional<CategoryEntity> existing = list.getCategories().stream()
                .filter(c -> compatible(c, categoryDTO)).findAny();
        if (existing.isEmpty()) {
            list.getCategories().add(categoryMapper.dtoToEntity(categoryDTO));
        } else {
            val category = existing.get();
            for (final ItemDTO itemDTO : categoryDTO.getItems()) {
                insertItem(category, itemDTO);
            }
        }
    }

    private void insertItem(final CategoryEntity category, final ItemDTO itemDTO) {
        if (category.getItems() == null) {
            category.setItems(new ArrayList<>());
        }
        val existing = category.getItems().stream()
                .filter(i -> compatible(i, itemDTO)).findAny();
        if (existing.isEmpty()) {
            category.getItems().add(itemMapper.dtoToEntity(itemDTO));
        } else {
            val quantity = existing.get().getQuantity();
            quantity.setAmount(quantity.getAmount() + itemDTO.getQuantity().getAmount());
        }
    }

    private boolean compatible(CategoryEntity entity, CategoryDTO dto) {
        return Objects.equals(entity.getName(), dto.getName())
                && Objects.equals(entity.getSymbol(), dto.getSymbol());
    }

    private boolean compatible(ItemEntity entity, ItemDTO dto) {
        return entity.isActive()
                && Objects.equals(entity.getName(), dto.getName())
                && Objects.equals(entity.getQuantity().getUnit(), dto.getQuantity().getUnit());
    }

    private String generateIdentifier() {
        return new String(
                Base64.getEncoder().encode(
                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
                )
        );
    }
}

package com.github.marctelllindner.list.model.mapping;

import com.github.marctelllindner.list.model.dtos.ItemDTO;
import com.github.marctelllindner.list.model.entities.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<ItemEntity, ItemDTO> {
    @Override
    public ItemDTO entityToDto(final ItemEntity itemEntity, final String identifier) {
        return ItemDTO.builder()
                .identifier(identifier)
                .name(itemEntity.getName())
                .quantity(
                        ItemDTO.Quantity.builder()
                                .amount(itemEntity.getQuantity().getAmount())
                                .unit(itemEntity.getQuantity().getUnit())
                                .build()
                )
                .active(itemEntity.isActive())
                .build();
    }

    @Override
    public ItemEntity dtoToEntity(final ItemDTO itemDTO) {
        return new ItemEntity()
                .setName(itemDTO.getName())
                .setQuantity(
                        new ItemEntity.Quantity()
                                .setAmount(itemDTO.getQuantity().getAmount())
                                .setUnit(itemDTO.getQuantity().getUnit())
                )
                .setActive(itemDTO.isActive());
    }
}

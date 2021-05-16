package com.github.marctelllindner.list.model.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class CategoryDTO {
    String name;
    String symbol;
    List<ItemDTO> items;

    String identifier;
}

package com.github.marctelllindner.list.model.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ItemDTO {
    String name;
    Quantity quantity;
    String identifier;

    @Value
    @Builder
    @Jacksonized
    public static class Quantity {
        double amount;
        String unit;
    }

    @Builder.Default
    boolean active = true;
}

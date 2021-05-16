package com.github.marctelllindner.list.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ItemEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private boolean active;

    @Embedded
    private Quantity quantity;

    @Data
    @Embeddable
    public static class Quantity {
        private double amount;
        private String unit;
    }
}

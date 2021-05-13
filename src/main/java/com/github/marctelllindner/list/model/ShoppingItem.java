package com.github.marctelllindner.list.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ShoppingItem {

    @Id
    @GeneratedValue
    private long id;

    private String name;

//    @ManyToOne
//    private ShoppingList list;

    @Embedded
    private Quantity quantity;

    @Data
    @Embeddable
    public static class Quantity {
        private int amount;
        private String unit;
    }

    private String category;
}

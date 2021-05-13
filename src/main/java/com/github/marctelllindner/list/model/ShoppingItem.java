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

    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
    private ShoppingList list;

    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
    private ShoppingCategory category;
}

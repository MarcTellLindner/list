package com.github.marctelllindner.list.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@Table
public class ShoppingCategory {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
    private ShoppingList list;

    @OneToMany(mappedBy = "category", cascade = ALL)
    private Collection<ShoppingItem> items;
}

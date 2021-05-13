package com.github.marctelllindner.list.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.*;

@Data
@Entity
@Table
public class ShoppingList {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "list", cascade = ALL)
    private Collection<ShoppingItem> items;

    @OneToMany(mappedBy = "list", cascade = ALL)
    private Collection<ShoppingCategory> categories;
}

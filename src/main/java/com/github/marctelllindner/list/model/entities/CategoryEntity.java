package com.github.marctelllindner.list.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@Table
public class CategoryEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String symbol;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "categoryId")
    private Collection<ItemEntity> items;
}

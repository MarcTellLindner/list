package com.github.marctelllindner.list.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.*;

@Data
@Entity
@Table
public class ListEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String identifier;

    private String name;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "listId")
    private Collection<CategoryEntity> categories;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "listId")
    private Collection<ItemEntity> items;
}

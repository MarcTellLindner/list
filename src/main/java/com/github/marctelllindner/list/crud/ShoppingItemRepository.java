package com.github.marctelllindner.list.crud;

import com.github.marctelllindner.list.model.ShoppingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemRepository extends CrudRepository<ShoppingItem, Long> {
}
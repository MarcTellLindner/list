package com.github.marctelllindner.list.service;

import com.github.marctelllindner.list.crud.ShoppingListRepository;
import com.github.marctelllindner.list.model.ShoppingList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingList createList(final String name) {
        final var shoppingList = new ShoppingList();
        shoppingList.setName(name);
        return shoppingListRepository.save(shoppingList);
    }

    public Optional<ShoppingList> getList(long id) {
        return shoppingListRepository.findById(id);
    }
}

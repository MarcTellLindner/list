package com.github.marctelllindner.list.service;

import com.github.marctelllindner.list.crud.ShoppingItemRepository;
import com.github.marctelllindner.list.crud.ShoppingListRepository;
import com.github.marctelllindner.list.model.ShoppingItem;
import com.github.marctelllindner.list.model.ShoppingList;
import com.github.marctelllindner.list.util.ErrorCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ShoppingListRepository listRepository;
    private final ShoppingItemRepository itemRepository;

    @Transactional
    public ShoppingList createList(final String name) {
        final var shoppingList = new ShoppingList();
        shoppingList.setName(name);
        return listRepository.save(shoppingList);
    }

    public ShoppingList getList(long id) throws ErrorCodeException {
        final var optional = listRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new ErrorCodeException(NOT_FOUND, "No list with this id");
    }

    @Transactional
    public ShoppingList addItem(final long id, final ShoppingItem item) throws ErrorCodeException {
        final var list = getList(id);

        list.getItems().add(item);

        itemRepository.save(item);
        return listRepository.save(list);
    }
}

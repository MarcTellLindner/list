package com.github.marctelllindner.list.rest;

import com.github.marctelllindner.list.model.ShoppingItem;
import com.github.marctelllindner.list.model.ShoppingList;
import com.github.marctelllindner.list.service.ListService;
import com.github.marctelllindner.list.util.ErrorCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    @GetMapping("/id/{id}")
    public ShoppingList getList(@PathVariable final long id) throws HttpStatusCodeException, ErrorCodeException {
        return listService.getList(id).orElseThrow(() -> new ErrorCodeException(NOT_FOUND, "No list with this id"));
    }

    @PostMapping("/name/{name}")
    public ShoppingList createShoppingList(@PathVariable final String name) {
        return listService.createList(name);
    }

    @PutMapping("/id/{id}")
    public ShoppingList addItem(@PathVariable final long id, @RequestBody final ShoppingItem item) {
        return null;
    }

    @ExceptionHandler(ErrorCodeException.class)
    public ResponseEntity<Object> handleErrorCodeException(final ErrorCodeException e) {
        log.error("Error: {}", e.getBody());
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getBody());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception e) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}

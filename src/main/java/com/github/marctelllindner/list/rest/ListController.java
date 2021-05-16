package com.github.marctelllindner.list.rest;

import com.github.marctelllindner.list.model.dtos.CategoryDTO;
import com.github.marctelllindner.list.model.dtos.ListDTO;
import com.github.marctelllindner.list.model.entities.ItemEntity;
import com.github.marctelllindner.list.model.entities.ListEntity;
import com.github.marctelllindner.list.service.ListService;
import com.github.marctelllindner.list.util.ErrorCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    @GetMapping("/id/{id}")
    public ListDTO getList(@PathVariable final String id) throws ErrorCodeException {
        return listService.getList(id);
    }

    @PostMapping("/name/{name}")
    public ListDTO createShoppingList(@PathVariable final String name) {
        return listService.createList(name);
    }

    @PostMapping("/id/{id}")
    public ListDTO addItems(@PathVariable final String id, @RequestBody final List<CategoryDTO> items) throws ErrorCodeException {
        return listService.addItems(id, items);
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

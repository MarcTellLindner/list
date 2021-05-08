package com.github.marctelllindner.list.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class Test {

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("OK");
    }

}

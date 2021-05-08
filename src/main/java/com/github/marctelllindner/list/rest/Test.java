package com.github.marctelllindner.list.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/ok")
    public String getOk() {
        return "ok";
    }

    @GetMapping("/fine")
    public String getFine() {
        return "fine";
    }

}

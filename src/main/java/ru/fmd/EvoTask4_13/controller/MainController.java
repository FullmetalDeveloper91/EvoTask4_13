package ru.fmd.EvoTask4_13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public String hello(){
        return "Hello, Web!";
    }
}

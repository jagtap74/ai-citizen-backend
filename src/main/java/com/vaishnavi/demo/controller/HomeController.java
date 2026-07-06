package com.vaishnavi.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to AI Citizen Assistant!";
    }

    @GetMapping("/about")
    public String about() {
        return "AI Citizen Assistant API is running.";
    }
}
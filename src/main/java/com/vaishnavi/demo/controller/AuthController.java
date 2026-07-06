package com.vaishnavi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaishnavi.demo.entity.User;
import com.vaishnavi.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.register(user);
    }
    
    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return userService.login(user.getEmail(), user.getPassword());
    }
}
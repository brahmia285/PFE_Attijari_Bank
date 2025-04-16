package com.satisfaction.controller;

import com.satisfaction.entity.User;
import com.satisfaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> userOptional = userService.login(username, password);
        if (userOptional.isPresent()) {
            return "Login successful. Welcome, " + userOptional.get().getUsername() + "!";
        } else {
            return "Invalid username or password.";
        }
    }

    // Register (si tu veux créer des users via Postman aussi)
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}

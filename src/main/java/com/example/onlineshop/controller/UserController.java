package com.example.onlineshop.controller;

import com.example.onlineshop.collection.entity.User;
import com.example.onlineshop.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }
}

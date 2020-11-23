package org.example.springelasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.example.springelasticsearch.entity.User;
import org.example.springelasticsearch.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    final private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestParam(name = "name") String name) {
        return userService.createUser(name);
    }
}

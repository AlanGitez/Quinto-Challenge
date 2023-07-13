package com.challenge.controllers;

import com.challenge.dto.UserDTO;
import com.challenge.entities.User;
import com.challenge.out.Response;
import com.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users/{userId}")
    public Response findById(@PathVariable int userId) {
        return service.findById(userId);
    }

    @GetMapping("/users/all")
    public Response getAll() {
        return service.getAll();
    }

    @PutMapping("/users/{userId}")
    public Response update(@PathVariable int userId, @RequestBody UserDTO body) {
        return service.update(body, userId);
    }

    @PostMapping("/users/add")
    public Response save(@RequestBody User body) {
        return service.save(body);
    }

    @DeleteMapping("/users/{userId}")
    public Response delete(@PathVariable int userId) {
        return service.delete(userId);
    }
}

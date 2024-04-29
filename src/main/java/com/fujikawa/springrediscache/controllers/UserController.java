package com.fujikawa.springrediscache.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fujikawa.springrediscache.models.User;
import com.fujikawa.springrediscache.repositories.UserRepository;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public Mono<User> getUser(@PathVariable("id") int id) {
        
        return userRepository.getUser(id);
    }

    @PutMapping("{id}")
    public Mono<User> updateUsername(
        @PathVariable("id") int id,
        @RequestBody String name) {
        return userRepository.updateUserName(id, name);
    }
}

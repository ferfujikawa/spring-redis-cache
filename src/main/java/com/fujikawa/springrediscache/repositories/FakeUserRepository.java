package com.fujikawa.springrediscache.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.fujikawa.springrediscache.models.User;

import reactor.core.publisher.Mono;

@Repository
public class FakeUserRepository implements UserRepository {

    private List<User> users = IntStream.range(1, 100)
        .boxed()
        .map(i -> new User(
            i,
            String.format("User %s", i),
            String.format("user%s@email.com", i)
        ))
        .collect(Collectors.toList());

    @Cacheable(value = "users", key = "#id")
    @Override
    public Mono<User> getUser(int id) {
        
        System.out.println("Called getUser()...");

        Optional<User> theUser = users
            .stream()
            .filter(user -> user.getId() == id)
            .findFirst();
        
        if (theUser.isPresent()) {
            return Mono.just(theUser.get());
        }

        return Mono.empty();
    }

    @CacheEvict(value = "users", key = "#id")
    public Mono<User> updateUserName(int id, String name) {
     
        System.out.println("Called updateUserName()...");
        
        Optional<User> theUser = users
            .stream()
            .filter(user -> user.getId() == id)
            .findFirst();
        
        if (theUser.isPresent()) {
            theUser.get().setName(name);
            return Mono.just(theUser.get());
        }

        return Mono.empty();
    }
}

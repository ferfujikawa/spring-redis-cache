package com.fujikawa.springrediscache.repositories;

import com.fujikawa.springrediscache.models.User;

import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> getUser(int id);
    Mono<User> updateUserName(int id, String name);
}

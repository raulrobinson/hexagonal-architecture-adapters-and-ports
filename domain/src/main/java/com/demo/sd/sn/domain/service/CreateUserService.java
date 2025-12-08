package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.model.User;
import com.demo.sd.sn.domain.port.out.OutUserRepositoryPort;
import reactor.core.publisher.Mono;

public class CreateUserService {

    private final OutUserRepositoryPort repository;

    public CreateUserService(
            OutUserRepositoryPort repository
    ) {
        this.repository = repository;
    }

    public Mono<User> save(User user) {
        System.out.println("[UserService] creating user: " + user);
        return repository.save(user);
    }
}

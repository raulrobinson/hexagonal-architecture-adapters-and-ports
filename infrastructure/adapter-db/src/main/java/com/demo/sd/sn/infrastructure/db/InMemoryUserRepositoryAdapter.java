package com.demo.sd.sn.infrastructure.db;

import com.demo.sd.sn.domain.model.User;
import com.demo.sd.sn.domain.port.out.OutUserRepositoryPort;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepositoryAdapter implements OutUserRepositoryPort {

    private final Map<String, User> db = new ConcurrentHashMap<>();

    @Override
    public Mono<User> save(User user) {
        System.out.println("[UserRepository] saving user: " + user);
        db.put(user.id(), user);
        return Mono.just(user);
    }
}


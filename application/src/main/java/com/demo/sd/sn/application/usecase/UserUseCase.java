package com.demo.sd.sn.application.usecase;

import com.demo.sd.sn.domain.model.User;
import com.demo.sd.sn.domain.port.in.InUserRepositoryPort;
import com.demo.sd.sn.domain.service.CreateUserService;
import reactor.core.publisher.Mono;

public class UserUseCase implements InUserRepositoryPort {

    private final CreateUserService createUserService;

    public UserUseCase(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @Override
    public Mono<User> save(User user) {
        System.out.println("[UserUseCase] saving user " + user);
        return createUserService.save(user);
    }
}

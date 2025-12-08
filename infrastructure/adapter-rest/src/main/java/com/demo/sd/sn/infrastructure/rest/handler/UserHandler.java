package com.demo.sd.sn.infrastructure.rest.handler;

import com.demo.sd.sn.domain.model.User;
import com.demo.sd.sn.domain.port.in.InUserRepositoryPort;
import com.demo.sd.sn.infrastructure.rest.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserHandler {

    private final InUserRepositoryPort inUserRepositoryPort;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreateUserRequest.class)
//                .map(r -> inUserRepositoryPort.save(toDomain(r.name(), r.email())))
//                .flatMap(user -> ServerResponse.ok().bodyValue(user));
                .flatMap(r -> inUserRepositoryPort.save(toDomain(r.name(), r.email())))
                .flatMap(savedUser ->
                        ServerResponse.ok()
                                .bodyValue(savedUser)
                );
    }

    private User toDomain(String name, String email) {
        System.out.println("[UserHandler] name: " + name + " -  email: " + email);
        return new User(UUID.randomUUID().toString(), name, email);
    }
}

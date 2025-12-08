package com.demo.sd.sn.infrastructure.rest.handler;

import com.demo.sd.sn.application.usecase.GetSecretsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecretsHandler {

    private final GetSecretsUseCase getSecretsUseCase;

    public Mono<ServerResponse> getSecrets(ServerRequest request) {
        String name = request.queryParam("name")
                .orElseThrow(() -> new IllegalArgumentException("Missing secret name"));

        return ServerResponse.ok()
                .body(
                        getSecretsUseCase.execute(name, String.class),
                        String.class
                );
    }
}

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

    private final GetSecretsUseCase secretsProvider;

    public Mono<ServerResponse> getSecret(ServerRequest request) {
        String name = request.queryParam("name")
                .orElseThrow(() -> new IllegalArgumentException("name required"));

        return Mono.fromSupplier(() -> secretsProvider.execute(name))
                .flatMap(secret -> ServerResponse.ok().bodyValue(secret));
    }
}

/*

Regla CLAVE de WebFlux

TODO lo que sale de un handler WebFlux debe ser Publisher
(Mono o Flux)

 */
package com.demo.sd.sn.infrastructure.rest.handler;

import com.demo.sd.sn.application.usecase.GetParameterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ParameterHandler {

    private final GetParameterUseCase getParameterUseCase;

    public Mono<ServerResponse> getParameter(ServerRequest request) {
        String name = request.queryParam("name")
                .orElseThrow(() -> new IllegalArgumentException("Missing parameter name"));

        return ServerResponse.ok()
                .body(
                        getParameterUseCase.execute(name, String.class),
                        String.class
                );
    }
}

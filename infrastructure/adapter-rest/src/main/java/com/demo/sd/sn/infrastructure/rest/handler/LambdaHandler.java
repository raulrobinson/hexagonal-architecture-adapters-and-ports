package com.demo.sd.sn.infrastructure.rest.handler;

import com.demo.sd.sn.application.usecase.LambdaUseCase;
import com.demo.sd.sn.application.usecase.ParamConfigurationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class LambdaHandler {

    private final LambdaUseCase lambdaUseCase;

    public Mono<ServerResponse> getInvokeLambda(ServerRequest request) {
        return ServerResponse.ok()
                .body(
                        request.bodyToMono(ParamConfigurationRequest.class)
                                .flatMap(lambdaUseCase::invokeGet),
                        Object.class
                )
                .doOnError(e -> log.error("Error invoking lambda", e));
    }

}

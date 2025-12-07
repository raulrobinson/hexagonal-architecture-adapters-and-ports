package com.demo.sd.sn.infrastructure.rest.handler;

import com.demo.sd.sn.domain.port.in.InJSONPlaceHolderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class JSONPlaceHolderHandler {

    private final InJSONPlaceHolderPort jsonPlaceHolder;

    public Mono<ServerResponse> getPosts(ServerRequest request) {
        return Mono.fromSupplier(jsonPlaceHolder::getPosts)
                .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic())
                .flatMap(posts -> ServerResponse.ok().bodyValue(posts));
    }
}

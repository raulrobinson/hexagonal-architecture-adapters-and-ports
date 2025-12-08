package com.demo.sd.sn.domain.port.out;

import reactor.core.publisher.Mono;

public interface OutLambdaPort {
    <T> Mono<T> invoke(String param, Class<T> responseType);
}

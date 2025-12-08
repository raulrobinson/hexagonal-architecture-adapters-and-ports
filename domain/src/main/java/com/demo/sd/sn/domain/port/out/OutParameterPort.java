package com.demo.sd.sn.domain.port.out;

import reactor.core.publisher.Mono;

public interface OutParameterPort {
    <T> Mono<T> getParameter(String parameterName, Class<T> clazz);
}

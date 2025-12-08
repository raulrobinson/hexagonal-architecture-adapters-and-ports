package com.demo.sd.sn.domain.port.out;

import reactor.core.publisher.Mono;

public interface OutSecretsManagerPort {
    <T> Mono<T> getSecrets(String secretName, Class<T> clazz);
}

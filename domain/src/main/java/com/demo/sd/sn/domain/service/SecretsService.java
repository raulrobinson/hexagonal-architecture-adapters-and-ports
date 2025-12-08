package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.port.out.OutSecretsManagerPort;
import reactor.core.publisher.Mono;

public class SecretsService {

    private final OutSecretsManagerPort outSecretsManagerPort;

    public SecretsService(OutSecretsManagerPort outSecretsManagerPort) {
        this.outSecretsManagerPort = outSecretsManagerPort;
    }

    public <T> Mono<T> get(String secretName, Class<T> clazz) {
        return outSecretsManagerPort.getSecrets(secretName, clazz);
    }
}

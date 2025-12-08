package com.demo.sd.sn.application.usecase;

import com.demo.sd.sn.domain.service.SecretsService;
import reactor.core.publisher.Mono;

public class GetSecretsUseCase {

    private final SecretsService secretsService;

    public GetSecretsUseCase(SecretsService secretsService) {
        this.secretsService = secretsService;
    }

    public <T> Mono<T> execute(String secretName, Class<T> clazz) {
        return secretsService.get(secretName, clazz);
    }
}

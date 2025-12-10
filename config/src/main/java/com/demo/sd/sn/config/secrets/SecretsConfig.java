package com.demo.sd.sn.config.secrets;

import com.demo.sd.sn.application.usecase.GetSecretsUseCase;
import com.demo.sd.sn.domain.port.out.OutSecretsPort;
import com.demo.sd.sn.domain.service.SecretsService;
import com.demo.sd.sn.infrastructure.secrets.SecretsProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import java.time.Duration;

@Configuration
public class SecretsConfig {

    @Bean
    public SecretsService secretsService(
            OutSecretsPort outSecretsPort
    ) {
        return new SecretsService(outSecretsPort);
    }

    @Bean
    public GetSecretsUseCase getSecretsUseCase(
            SecretsService secretsService
    ) {
        return new GetSecretsUseCase(secretsService);
    }

    @Bean
    public OutSecretsPort outSecretsManagerPort(
            SecretsManagerClient secretsManagerClient,
            Cache<String, String> cache
    ) {
        return new SecretsProvider(secretsManagerClient, cache);
    }

    @Bean
    public Cache<String, String> secretsCache() {
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(Duration.ofMinutes(5))
                .build();
    }
}

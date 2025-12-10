package com.demo.sd.sn.infrastructure.secrets;

import com.demo.sd.sn.domain.port.out.OutSecretsPort;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class SecretsProvider implements OutSecretsPort {

    private final SecretsManagerClient client;
    private final Cache<String, String> cache;

    public SecretsProvider(SecretsManagerClient client) {
        this.client = client;
        this.cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    public String getSecret(String name) {
        return cache.get(name, this::loadSecret);
    }

    private String loadSecret(String name) {
        return client.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId(name)
                        .build()
        ).secretString();
    }
}

package com.demo.sd.sn.infrastructure.secrets;

import com.demo.sd.sn.domain.port.out.OutSecretsManagerPort;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
@AllArgsConstructor
public class SecretsManagerAdapter implements OutSecretsManagerPort {

    private final SecretsManagerAsyncClient secretsClient;
    private final ObjectMapper mapper;

    @Override
    public <T> Mono<T> getSecrets(String secretName, Class<T> clazz) {

        GetSecretValueRequest request = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        return Mono.fromFuture(secretsClient.getSecretValue(request))
                .map(response -> extractSecretValue(response))
                .doOnSubscribe(unused ->
                        log.info("Fetching secret: {}", secretName))
                .doOnSuccess(unused ->
                        log.info("Secret fetched: {}", secretName))
                .flatMap(value -> convert(value, clazz))
                .onErrorResume(ex -> {
                    log.error("Error getting secret {}", secretName, ex);
                    return Mono.error(ex);
                });
    }

    private String extractSecretValue(
            software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse response
    ) {
        if (response.secretString() != null) {
            return response.secretString();
        }

        // Binario (base64)
        SdkBytes binary = response.secretBinary();
        return new String(
                Base64.getDecoder().decode(binary.asByteArray()),
                StandardCharsets.UTF_8
        );
    }

    @SuppressWarnings("unchecked")
    private <T> Mono<T> convert(String value, Class<T> clazz) {
        if (clazz.equals(String.class)) {
            return Mono.just((T) value);
        }
        return Mono.fromCallable(() -> mapper.readValue(value, clazz));
    }
}

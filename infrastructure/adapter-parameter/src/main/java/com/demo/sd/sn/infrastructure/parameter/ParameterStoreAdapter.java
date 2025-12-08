package com.demo.sd.sn.infrastructure.parameter;

import com.demo.sd.sn.domain.port.out.OutParameterPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Slf4j
@Component
@AllArgsConstructor
public class ParameterStoreAdapter implements OutParameterPort {

    private final SsmAsyncClient ssmAsyncClient;
    private final ObjectMapper mapper;

    @Override
    public <T> Mono<T> getParameter(String parameterName, Class<T> clazz) {

        GetParameterRequest request = GetParameterRequest.builder()
                .name(parameterName)
                .withDecryption(true)   // ✅ IMPORTANTE para SecureString
                .build();

        return Mono.fromFuture(ssmAsyncClient.getParameter(request))
                .map(response -> response.parameter().value())
                .doOnSubscribe(unused ->
                        log.info("Fetching parameter: {}", parameterName))
                .doOnSuccess(unused ->
                        log.info("Parameter fetched: {}", parameterName))
                .flatMap(value -> convert(value, clazz))
                .onErrorResume(ex -> {
                    log.error("Error getting parameter {}", parameterName, ex);
                    return Mono.error(ex);
                });
    }

    @SuppressWarnings("unchecked")
    private <T> Mono<T> convert(String value, Class<T> clazz) {

        if (clazz.equals(String.class)) {
            return Mono.just((T) value);
        }

        return Mono.fromCallable(() -> mapper.readValue(value, clazz));
    }

}

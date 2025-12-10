package com.demo.sd.sn.infrastructure.parameter;

import com.demo.sd.sn.domain.port.out.OutParameterPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParameterStoreAdapter implements OutParameterPort {

    private final SsmClient ssmClient;
    private final Cache<String, String> cache;
    private final ObjectMapper mapper;

    @Override
    public <T> T getParameter(String parameterName, Class<T> clazz) {

        String value = cache.get(parameterName, this::loadParameter);

        return convert(value, clazz);
    }

    private String loadParameter(String parameterName) {
        try {
            log.info("[PARAMS] Loading parameter: {}", parameterName);

            return ssmClient.getParameter(
                    GetParameterRequest.builder()
                            .name(parameterName)
                            .withDecryption(true)
                            .build()
            ).parameter().value();

        } catch (Exception e) {
            log.error("[PARAMS] Error loading parameter {}", parameterName, e);
            throw new IllegalStateException("Unable to load parameter: " + parameterName, e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T convert(String value, Class<T> clazz) {
        try {
            if (clazz.equals(String.class)) {
                return (T) value;
            }
            return mapper.readValue(value, clazz);
        } catch (Exception e) {
            throw new IllegalStateException("Error converting parameter value to " + clazz.getSimpleName(), e);
        }
    }
}

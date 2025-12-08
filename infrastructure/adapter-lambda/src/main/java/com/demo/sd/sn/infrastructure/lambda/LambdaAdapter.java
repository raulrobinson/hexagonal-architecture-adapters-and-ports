package com.demo.sd.sn.infrastructure.lambda;

import com.demo.sd.sn.domain.port.out.OutLambdaPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;

import java.util.Map;

@Slf4j
public class LambdaAdapter implements OutLambdaPort {

    private final LambdaAsyncClient client;
    private final ObjectMapper mapper;
    private final String functionName;

    public LambdaAdapter(
            LambdaAsyncClient client,
            ObjectMapper mapper,
            String functionName
    ) {
        this.client = client;
        this.mapper = mapper;
        this.functionName = functionName;
    }

    @Override
    public <T> Mono<T> invoke(String param, Class<T> responseType) {

        String payload = "{\"param\":\"" + param + "\"}";

        InvokeRequest request = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        return Mono.fromFuture(client.invoke(request))
                .flatMap(response -> parseResponse(response, responseType));
    }

    @SuppressWarnings("unchecked")
    private <T> Mono<T> parseResponse(
            software.amazon.awssdk.services.lambda.model.InvokeResponse response,
            Class<T> responseType
    ) {

        String rawPayload = response.payload().asUtf8String();

        return Mono.fromCallable(() -> {
            Map<String, Object> envelope =
                    mapper.readValue(rawPayload, Map.class);

            Object body = envelope.get("body");

            if (body instanceof String bodyStr) {
                // API Gateway suele mandar string JSON
                return mapper.readValue(bodyStr, responseType);
            }

            // body ya viene como objeto
            return mapper.convertValue(body, responseType);
        });
    }
}

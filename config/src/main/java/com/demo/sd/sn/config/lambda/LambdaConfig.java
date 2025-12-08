package com.demo.sd.sn.config.lambda;

import com.demo.sd.sn.application.usecase.LambdaUseCase;
import com.demo.sd.sn.domain.port.out.OutLambdaPort;
import com.demo.sd.sn.domain.service.LambdaService;
import com.demo.sd.sn.infrastructure.lambda.LambdaAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;

public class LambdaConfig {

    @Bean
    public OutLambdaPort outLambdaPort(
            LambdaAsyncClient client,
            ObjectMapper objectMapper,
            @Value("${aws.lambda.function-name}") String functionName
    ) {
        return new LambdaAdapter(client, objectMapper, functionName);
    }

    @Bean
    public LambdaService lambdaService(
            OutLambdaPort outLambdaPort
    ) {
        return new LambdaService(outLambdaPort);
    }

    @Bean
    public LambdaUseCase lambdaUseCase(LambdaService lambdaService) {
        return new LambdaUseCase(lambdaService);
    }

//    @Bean
//    public LambdaUseCase getParameterUseCase(
//            LambdaService lambdaService
//    ) {
//        return new LambdaUseCase(lambdaService);
//    }

//    @Bean
//    public OutLambdaPort outLambdaPort(
//            LambdaAsyncClient lambdaAsyncClient,
//            ObjectMapper objectMapper
//    ) {
//        return new LambdaAdapter(lambdaAsyncClient, objectMapper);
//    }
}

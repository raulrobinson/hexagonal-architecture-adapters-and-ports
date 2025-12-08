package com.demo.sd.sn.config.parameter;

import com.demo.sd.sn.application.usecase.GetParameterUseCase;
import com.demo.sd.sn.domain.port.out.OutParameterPort;
import com.demo.sd.sn.domain.service.ParameterService;
import com.demo.sd.sn.infrastructure.parameter.ParameterStoreAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;

@Configuration
public class ParameterConfig {

    @Bean
    public ParameterService parameterService(
            OutParameterPort outParameterPort
    ) {
        return new ParameterService(outParameterPort);
    }

    @Bean
    public GetParameterUseCase getParameterUseCase(
            ParameterService parameterService
    ) {
        return new GetParameterUseCase(parameterService);
    }

    @Bean
    public OutParameterPort outParameterPort(
            SsmAsyncClient ssmAsyncClient,
            ObjectMapper objectMapper
    ) {
        return new ParameterStoreAdapter(ssmAsyncClient, objectMapper);
    }
}

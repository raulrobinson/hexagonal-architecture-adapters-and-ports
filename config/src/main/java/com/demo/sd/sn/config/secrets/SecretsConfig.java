package com.demo.sd.sn.config.secrets;

import com.demo.sd.sn.application.usecase.GetSecretsUseCase;
import com.demo.sd.sn.domain.port.out.OutSecretsManagerPort;
import com.demo.sd.sn.domain.service.SecretsService;
import com.demo.sd.sn.infrastructure.secrets.SecretsManagerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient;

@Configuration
public class SecretsConfig {

    @Bean
    public SecretsService secretsService(
            OutSecretsManagerPort outSecretsManagerPort
    ) {
        return new SecretsService(outSecretsManagerPort);
    }

    @Bean
    public GetSecretsUseCase getSecretsUseCase(
            SecretsService secretsService
    ) {
        return new GetSecretsUseCase(secretsService);
    }

    @Bean
    public OutSecretsManagerPort outSecretsManagerPort(
            SecretsManagerAsyncClient secretsManagerAsyncClient,
            ObjectMapper objectMapper
    ) {
        return new SecretsManagerAdapter(secretsManagerAsyncClient, objectMapper);
    }
}

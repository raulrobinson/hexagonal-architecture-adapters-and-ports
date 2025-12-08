package com.demo.sd.sn.config.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerAsyncClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    /**
     * Cliente para entorno local (LocalStack)
     */
    @Bean
    @Profile("local")
    public SecretsManagerAsyncClient SecretsManagerAsyncClientLocal(
            @Value("${aws.region}") String region,
            @Value("${aws.endpoint}") String endpoint
    ) {
        return SecretsManagerAsyncClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .build();
    }

    /**
     * Cliente para entorno dev (AWS SSO)
     */
    @Bean
    @Profile("poc")
    public SecretsManagerAsyncClient SecretsManagerAsyncClientPoc(
            @Value("${aws.region}") String region,
            @Value("${aws.profile}") String awsProfile
    ) {
        return SecretsManagerAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(ProfileCredentialsProvider.create(awsProfile))
                .build();
    }

    /**
     * Fallback si no hay profile activo (usa DefaultCredentialsProvider)
     */
    @Bean
    @Profile("!local & !poc")
    public SecretsManagerAsyncClient SecretsManagerAsyncClient() {
        return SecretsManagerAsyncClient.builder()
                .region(DefaultAwsRegionProviderChain.builder().build().getRegion())
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

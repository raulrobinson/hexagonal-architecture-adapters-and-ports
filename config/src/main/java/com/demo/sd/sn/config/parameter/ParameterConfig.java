package com.demo.sd.sn.config.parameter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;

import java.net.URI;

@Configuration
public class ParameterConfig {

    /**
     * Cliente para entorno local (LocalStack)
     */
    @Bean
    @Profile("local")
    public SsmAsyncClient SsmAsyncClientLocal(
            @Value("${aws.region}") String region,
            @Value("${aws.endpoint}") String endpoint
    ) {
        return SsmAsyncClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .build();
    }

    /**
     * Cliente para entorno dev (AWS SSO)
     */
    @Bean
    @Profile("poc")
    public SsmAsyncClient SsmAsyncClientPoc(
            @Value("${aws.region}") String region,
            @Value("${aws.profile}") String awsProfile
    ) {
        return SsmAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(ProfileCredentialsProvider.create(awsProfile))
                .build();
    }

    /**
     * Fallback si no hay profile activo (usa DefaultCredentialsProvider)
     */
    @Bean
    @Profile("!local & !poc")
    public SsmAsyncClient SsmAsyncClient() {
        return SsmAsyncClient.builder()
                .region(DefaultAwsRegionProviderChain.builder().build().getRegion())
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}

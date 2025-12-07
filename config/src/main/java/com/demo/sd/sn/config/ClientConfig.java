package com.demo.sd.sn.config;

import com.demo.sd.sn.domain.port.out.OutJSONPlaceHolderClientPort;
import com.demo.sd.sn.infrastructure.client.JSONPlaceHolderClientAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient creditScoreWebClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    @Bean
    public OutJSONPlaceHolderClientPort jsonPlaceHolderClient(
            WebClient jsonPlaceHolderWebClient
    ) {
        return new JSONPlaceHolderClientAdapter(jsonPlaceHolderWebClient);
    }
}

package com.demo.sd.sn.config;

import com.demo.sd.sn.application.usecase.JSONPlaceHolderUseCase;
import com.demo.sd.sn.domain.port.out.OutJSONPlaceHolderClientPort;
import com.demo.sd.sn.domain.service.JSONPlaceHolderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONPlaceHolderConfig {

    @Bean
    public JSONPlaceHolderService jsonPlaceHolderService(
            OutJSONPlaceHolderClientPort client
    ) {
        return new JSONPlaceHolderService(client);
    }

    @Bean
    public JSONPlaceHolderUseCase jsonPlaceHolderUseCase(
            JSONPlaceHolderService service
    ) {
        return new JSONPlaceHolderUseCase(service);
    }
}

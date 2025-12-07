package com.demo.sd.sn.config;

import com.demo.sd.sn.application.usecase.JSONPlaceHolderUseCase;
import com.demo.sd.sn.infrastructure.rest.handler.JSONPlaceHolderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestHandlerConfig {

    @Bean
    public JSONPlaceHolderHandler jsonPlaceHolderHandler(
            JSONPlaceHolderUseCase useCase
    ) {
        return new JSONPlaceHolderHandler(useCase);
    }
}

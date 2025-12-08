package com.demo.sd.sn.config.rest;

import com.demo.sd.sn.application.usecase.GetParameterUseCase;
import com.demo.sd.sn.application.usecase.JSONPlaceHolderUseCase;
import com.demo.sd.sn.application.usecase.UserUseCase;
import com.demo.sd.sn.infrastructure.rest.handler.JSONPlaceHolderHandler;
import com.demo.sd.sn.infrastructure.rest.handler.ParameterHandler;
import com.demo.sd.sn.infrastructure.rest.handler.UserHandler;
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

    @Bean
    public UserHandler userHandler(
            UserUseCase useCase
    ) {
        return new UserHandler(useCase);
    }

    @Bean
    public ParameterHandler parameterHandler(
            GetParameterUseCase getParameterUseCase
    ) {
        return new ParameterHandler(getParameterUseCase);
    }
}

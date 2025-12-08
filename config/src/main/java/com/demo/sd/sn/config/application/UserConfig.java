package com.demo.sd.sn.config.application;

import com.demo.sd.sn.application.usecase.UserUseCase;
import com.demo.sd.sn.domain.port.out.OutUserRepositoryPort;
import com.demo.sd.sn.domain.service.CreateUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserUseCase useCase(CreateUserService createUserService) {
        return new UserUseCase(createUserService);
    }

    @Bean
    public CreateUserService createUserService(OutUserRepositoryPort repository) {
        return new CreateUserService(repository);
    }
}

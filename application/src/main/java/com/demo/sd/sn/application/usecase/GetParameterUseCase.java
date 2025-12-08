package com.demo.sd.sn.application.usecase;

import com.demo.sd.sn.domain.service.ParameterService;
import reactor.core.publisher.Mono;

public class GetParameterUseCase {

    private final ParameterService parameterService;

    public GetParameterUseCase(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public <T> Mono<T> execute(String name, Class<T> clazz) {
        return parameterService.get(name, clazz);
    }
}

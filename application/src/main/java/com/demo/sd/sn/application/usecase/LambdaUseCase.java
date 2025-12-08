package com.demo.sd.sn.application.usecase;

import com.demo.sd.sn.domain.service.LambdaService;
import reactor.core.publisher.Mono;

public class LambdaUseCase {

    private final LambdaService lambdaService;

    public LambdaUseCase(LambdaService lambdaService) {
        this.lambdaService = lambdaService;
    }

    public Mono<Object> invokeGet(ParamConfigurationRequest request) {
        return lambdaService.invoke(
                request.getName(),
                Object.class
        );
    }
}

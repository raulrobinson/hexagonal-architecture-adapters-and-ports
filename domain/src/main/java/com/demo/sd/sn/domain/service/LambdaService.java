package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.port.out.OutLambdaPort;
import reactor.core.publisher.Mono;

public class LambdaService {

    private final OutLambdaPort lambdaPort;

    public LambdaService(OutLambdaPort lambdaPort) {
        this.lambdaPort = lambdaPort;
    }

    public <T> Mono<T> invoke(String param, Class<T> responseType) {
        return lambdaPort.invoke(param, responseType);
    }
}

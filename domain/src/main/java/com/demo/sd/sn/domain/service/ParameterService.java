package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.port.out.OutParameterPort;
import reactor.core.publisher.Mono;

public class ParameterService {

    private final OutParameterPort parameterPort;

    public ParameterService(OutParameterPort parameterPort) {
        this.parameterPort = parameterPort;
    }

    public <T> Mono<T> get(String name, Class<T> clazz) {
        return parameterPort.getParameter(name, clazz);
    }
}

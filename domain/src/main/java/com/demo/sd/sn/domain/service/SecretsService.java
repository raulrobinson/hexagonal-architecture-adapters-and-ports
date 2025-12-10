package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.port.out.OutSecretsPort;
import reactor.core.publisher.Mono;

public class SecretsService {

    private final OutSecretsPort outSecretsPort;

    public SecretsService(OutSecretsPort outSecretsPort) {
        this.outSecretsPort = outSecretsPort;
    }

    public String get(String secretName) {
        return outSecretsPort.getSecret(secretName);
    }
}

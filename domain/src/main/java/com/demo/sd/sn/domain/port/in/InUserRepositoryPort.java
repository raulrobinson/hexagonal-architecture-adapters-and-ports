package com.demo.sd.sn.domain.port.in;

import com.demo.sd.sn.domain.model.User;
import reactor.core.publisher.Mono;

public interface InUserRepositoryPort {
    Mono<User> save(User user);
}

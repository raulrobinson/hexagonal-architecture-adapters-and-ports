package com.demo.sd.sn.domain.port.out;

import com.demo.sd.sn.domain.model.User;
import reactor.core.publisher.Mono;

public interface OutUserRepositoryPort {
    Mono<User> save(User user);
}

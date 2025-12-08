package com.demo.sd.sn.domain.port.out;

import com.demo.sd.sn.domain.model.Post;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OutJSONPlaceHolderClientPort {
    Mono<List<Post>> getPosts();
}
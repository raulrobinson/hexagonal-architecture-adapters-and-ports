package com.demo.sd.sn.domain.port.in;

import com.demo.sd.sn.domain.model.Post;
import reactor.core.publisher.Mono;

import java.util.List;

public interface InJSONPlaceHolderPort {
    Mono<List<Post>> getPosts();
}

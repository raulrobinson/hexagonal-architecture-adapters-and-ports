package com.demo.sd.sn.infrastructure.client;

import com.demo.sd.sn.domain.model.Post;
import com.demo.sd.sn.domain.port.out.OutJSONPlaceHolderClientPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Component
public class JSONPlaceHolderClientAdapter implements OutJSONPlaceHolderClientPort {

    private final WebClient webClient;

    public JSONPlaceHolderClientAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Post> getPosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList()
                .block(); // permitido SOLO en adapter
    }
}

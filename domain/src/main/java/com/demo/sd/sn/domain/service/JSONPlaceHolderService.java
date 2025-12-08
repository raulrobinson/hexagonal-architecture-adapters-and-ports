package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.model.Post;
import com.demo.sd.sn.domain.port.out.OutJSONPlaceHolderClientPort;
import reactor.core.publisher.Mono;

import java.util.List;

public class JSONPlaceHolderService {

    private final OutJSONPlaceHolderClientPort jsonPlaceHolderClient;

    public JSONPlaceHolderService(OutJSONPlaceHolderClientPort jsonPlaceHolderClient) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
    }

    public Mono<List<Post>> getPosts() {
        return jsonPlaceHolderClient.getPosts();
    }
}

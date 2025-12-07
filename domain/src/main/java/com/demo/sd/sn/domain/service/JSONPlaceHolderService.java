package com.demo.sd.sn.domain.service;

import com.demo.sd.sn.domain.model.Post;
import com.demo.sd.sn.domain.port.out.OutJSONPlaceHolderClientPort;

import java.util.List;

public class JSONPlaceHolderService {

    private final OutJSONPlaceHolderClientPort client;

    public JSONPlaceHolderService(OutJSONPlaceHolderClientPort client) {
        this.client = client;
    }

    public List<Post> getPosts() {
        return client.getPosts();
    }
}

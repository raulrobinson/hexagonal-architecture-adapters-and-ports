package com.demo.sd.sn.application.usecase;

import com.demo.sd.sn.domain.port.in.InJSONPlaceHolderPort;
import com.demo.sd.sn.domain.model.Post;
import com.demo.sd.sn.domain.service.JSONPlaceHolderService;

import java.util.List;

public class JSONPlaceHolderUseCase implements InJSONPlaceHolderPort {

    private final JSONPlaceHolderService jsonPlaceHolderService;

    public JSONPlaceHolderUseCase(JSONPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    @Override
    public List<Post> getPosts() {
        return jsonPlaceHolderService.getPosts();
    }
}

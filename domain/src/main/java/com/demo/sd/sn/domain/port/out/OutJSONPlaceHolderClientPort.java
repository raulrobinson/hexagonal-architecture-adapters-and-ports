package com.demo.sd.sn.domain.port.out;

import com.demo.sd.sn.domain.model.Post;

import java.util.List;

public interface OutJSONPlaceHolderClientPort {
    List<Post> getPosts();
}
package com.demo.sd.sn.domain.port.in;

import com.demo.sd.sn.domain.model.Post;

import java.util.List;

public interface InJSONPlaceHolderPort {
    List<Post> getPosts();
}

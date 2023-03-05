package br.com.cwi.cwitter.factories;


import br.com.cwi.cwitter.domain.Post;
import br.com.cwi.cwitter.domain.PostPrivacy;

import java.time.LocalDateTime;

public class PostFactory {

    public static Post get() {
        Post post = new Post();
        post.setId(SimpleFactory.getRandomLong());
        post.setPostPrivacy(PostPrivacy.PUBLIC);
        post.setInclusionDate(LocalDateTime.now());
        post.setUserId(SimpleFactory.getRandomLong());
        return post;
    }
}

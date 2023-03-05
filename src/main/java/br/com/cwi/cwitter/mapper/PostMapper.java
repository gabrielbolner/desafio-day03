package br.com.cwi.cwitter.mapper;


import br.com.cwi.cwitter.controller.request.AddPostRequest;
import br.com.cwi.cwitter.domain.Post;

import java.time.LocalDateTime;

public class PostMapper {
    public static Post toResponse(AddPostRequest request) {
        return Post.builder()
                .writtenContent(request.getWrittenContent())
                .imageContent(request.getImageContent())
                .inclusionDate(LocalDateTime.now())
                .postPrivacy(request.getPostPrivacy())
                .build();
    }
}

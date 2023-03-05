package br.com.cwi.cwitter.mapper;

import br.com.cwi.cwitter.controller.request.AddCommentRequest;
import br.com.cwi.cwitter.domain.Comment;

public class CommentMapper {
    public static Comment toEntity(AddCommentRequest request) {
        return Comment.builder()
                .comment(request.getComment())
                .userId(request.getUserId())
                .build();
    }
}

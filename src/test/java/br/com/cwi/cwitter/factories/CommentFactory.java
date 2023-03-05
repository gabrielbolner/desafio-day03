package br.com.cwi.cwitter.factories;

import br.com.cwi.cwitter.controller.request.AddCommentRequest;
import br.com.cwi.cwitter.domain.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentFactory {

    public static Comment get() {
        Comment comment = new Comment();
        comment.setId(SimpleFactory.getRandomLong());
        comment.setComment("Testando 1,2,3...");
        comment.setPostId(SimpleFactory.getRandomLong());
        comment.setUserId(SimpleFactory.getRandomLong());
        return comment;
    }

    public static AddCommentRequest getAddComment() {
        AddCommentRequest request = new AddCommentRequest();
        request.setComment("Testando 1,2,3...");
        request.setUserId(SimpleFactory.getRandomLong());
        return request;
    }

    public static List<Comment> getListOf(int i, Long postId) {

        List<Comment> comments = new ArrayList<>();
        for (int x = 0; x < i; x++) {

            comments.add(get());
        }
        return comments;
    }
}

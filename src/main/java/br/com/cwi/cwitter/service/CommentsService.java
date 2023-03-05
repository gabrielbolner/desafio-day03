package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.controller.request.AddCommentRequest;
import br.com.cwi.cwitter.domain.Comment;
import br.com.cwi.cwitter.mapper.CommentMapper;
import br.com.cwi.cwitter.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comment> getComments(Long id) {
        return commentsRepository.findAllByPostId(id);
    }

    public void addComment(Long id, AddCommentRequest request) {

        Comment comment = CommentMapper.toEntity(request);
        comment.setPostId(id);

        commentsRepository.save(comment);
    }
}

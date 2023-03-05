package br.com.cwi.cwitter.controller;

import br.com.cwi.cwitter.controller.request.AddCommentRequest;
import br.com.cwi.cwitter.domain.Comment;
import br.com.cwi.cwitter.security.service.AuthenticatedUserService;
import br.com.cwi.cwitter.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private AuthenticatedUserService service;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/{id}")
    public List<Comment> getComments(@PathVariable Long id) {
        return commentsService.getComments(id);
    }

    @PostMapping("/{id}/comment")
    public void addComment(@PathVariable Long id, @RequestBody AddCommentRequest request) {
        commentsService.addComment(id,request);
    }
}

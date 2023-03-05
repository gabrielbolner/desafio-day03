package br.com.cwi.cwitter.controller;

import br.com.cwi.cwitter.controller.request.AddPostRequest;
import br.com.cwi.cwitter.domain.Post;
import br.com.cwi.cwitter.security.service.AuthenticatedUserService;
import br.com.cwi.cwitter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private AuthenticatedUserService service;

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Page<Post> getPosts(@PathVariable Long id, Pageable pageable) {
        return postService.getPosts(id,pageable);
    }

    @PostMapping("/{id}")
    public void addPost(@PathVariable Long id, @RequestBody AddPostRequest request) {
        postService.addPost(id,request);
    }

    @PostMapping("/{postId}/like/{userId}")
    public void likePost(@PathVariable Long postId,@PathVariable Long userId) {
        postService.likePost(postId,userId);
    }

    @PostMapping("/{postId}/unlike/{userId}")
    public void removeLike(@PathVariable Long postId,@PathVariable Long userId) {
        postService.removeLike(postId,userId);
    }

    @GetMapping("/{id}/likes")
    public int getPostLikesQuantity(@PathVariable Long id) {
        return postService.showPostLikesReceived(id);
    }
}

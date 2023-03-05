package br.com.cwi.cwitter.service;


import br.com.cwi.cwitter.controller.request.AddPostRequest;
import br.com.cwi.cwitter.domain.Likes;
import br.com.cwi.cwitter.domain.Post;
import br.com.cwi.cwitter.mapper.PostMapper;
import br.com.cwi.cwitter.repository.FriendsRepository;
import br.com.cwi.cwitter.repository.LikesRepository;
import br.com.cwi.cwitter.repository.PostsRepository;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.mapper.UserMapper;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private FriendsSearchService friendsSearchService;

    @Autowired
    private SearchUserInformationService searchUserInformationService;

    public Page<Post> getPosts(Long id, Pageable pageable) {

        User user = userRepository.findById(id).get();
        UserResponse userResponse = UserMapper.toResponse(user);

        List<UserResponse> friendsList = friendsSearchService.getAllFriends(id);

        friendsList.add(userResponse);

        List<Long> allFriendsIds = friendsList.stream().map(friend -> friend.getId()).collect(Collectors.toList());

        return postsRepository.findAllByUserIdInOrderByInclusionDateDesc(allFriendsIds, pageable);
    }

    public void addPost(Long id, AddPostRequest request) {

        Post post = PostMapper.toResponse(request);
        post.setInclusionDate(LocalDateTime.now());
        post.setUserId(id);
        postsRepository.save(post);
    }

    public void likePost(Long postId, Long userId) {

        Likes like = new Likes();
        like.setPostId(postId);
        like.setUserId(userId);

        likesRepository.save(like);
    }

    public int showPostLikesReceived(Long id) {
        List<Likes> allLikesPost = likesRepository.findAllByPostId(id);
        return allLikesPost.size();
    }

    public void removeLike(Long postId, Long userId) {

        Likes previouslyLiked = likesRepository.findByPostIdAndUserId(postId, userId);

        likesRepository.delete(previouslyLiked);
    }
}

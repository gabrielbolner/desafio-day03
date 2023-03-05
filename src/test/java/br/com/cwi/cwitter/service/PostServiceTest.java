package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.domain.Likes;
import br.com.cwi.cwitter.repository.LikesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private LikesRepository likesRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("Deve mostrar a quantidade de likes recebidos por um post")
    void deveMostrarAQuantidadeDeLikesRecebidosPorUmPost() {
        Long postId = 1L;
        when(likesRepository.findAllByPostId(postId)).thenReturn(Arrays.asList(new Likes(), new Likes()));

        int likesReceived = postService.showPostLikesReceived(postId);

        verify(likesRepository).findAllByPostId(postId);
        assertEquals(2, likesReceived);
    }

    @Test
    public void testShowPostLikesReceived() {
        Long postId = 1L;

        List<Likes> likesList = new ArrayList<>();
        likesList.add(new Likes());
        likesList.add(new Likes());

        Mockito.when(likesRepository.findAllByPostId(postId)).thenReturn(likesList);

        int likesReceived = postService.showPostLikesReceived(postId);

        assertEquals(likesList.size(), likesReceived);

        Mockito.verify(likesRepository, Mockito.times(1)).findAllByPostId(postId);
    }

    @Test
    public void testRemoveLike() {
        Long postId = 1L;
        Long userId = 1L;

        Likes previouslyLiked = new Likes();

        Mockito.when(likesRepository.findByPostIdAndUserId(postId, userId)).thenReturn(previouslyLiked);

        postService.removeLike(postId, userId);

        Mockito.verify(likesRepository, Mockito.times(1)).findByPostIdAndUserId(postId, userId);
        Mockito.verify(likesRepository, Mockito.times(1)).delete(previouslyLiked);
    }
}


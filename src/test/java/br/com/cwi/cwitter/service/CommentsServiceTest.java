package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.controller.request.AddCommentRequest;
import br.com.cwi.cwitter.domain.Comment;
import br.com.cwi.cwitter.factories.CommentFactory;
import br.com.cwi.cwitter.factories.SimpleFactory;
import br.com.cwi.cwitter.repository.CommentsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentsServiceTest {

    @Mock
    private CommentsRepository commentsRepository;

    @InjectMocks
    private CommentsService commentsService;

    @Captor
    private ArgumentCaptor<Comment> commentCaptor;

    @Test
    @DisplayName("Deve adicionar um novo comentário")
    void deveAdicionarUmNovoComentario() {
        Long postId = 1L;
        AddCommentRequest request = AddCommentRequest
                .builder()
                .comment("Comentário de teste")
                .userId(SimpleFactory.getRandomLong())
                .build();

        commentsService.addComment(postId, request);
        verify(commentsRepository).save(commentCaptor.capture());

        assertEquals(request.getComment(), commentCaptor.getValue().getComment());
        assertEquals(postId, commentCaptor.getValue().getPostId());
    }

    @Test
    @DisplayName("Deve buscar os comentários de um post")
    void deveBuscarOsComentariosDeUmPost() {
        Long postId = 1L;
        List<Comment> comments = CommentFactory.getListOf(5, postId);
        when(commentsRepository.findAllByPostId(postId)).thenReturn(comments);

        List<Comment> result = commentsService.getComments(postId);
        assertEquals(comments.size(), result.size());
        assertIterableEquals(comments, result);
    }
}

package br.com.cwi.cwitter.mapper;

import br.com.cwi.cwitter.controller.request.AddCommentRequest;
import br.com.cwi.cwitter.domain.Comment;
import br.com.cwi.cwitter.factories.CommentFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CommentMapperTest {

    @Test
    @DisplayName("Deve retornar o entity com as informações obtidas do request")
    void deveRetornarEntityComInfo() {

        AddCommentRequest request = CommentFactory.getAddComment();

        Comment comment = CommentMapper.toEntity(request);

        assertEquals(comment.getUserId(), request.getUserId());
        assertEquals(comment.getComment(), request.getComment());
    }

        @Test
        @DisplayName("Deve converter um AddCommentRequest com comentário vazio em um Comment")
        void deveConverterUmAddCommentRequestComComentarioVazioEmUmComment() {
            AddCommentRequest request = AddCommentRequest.builder()
                    .comment("")
                    .userId(1L)
                    .build();

            Comment comment = CommentMapper.toEntity(request);

            assertEquals(request.getComment(), comment.getComment());
            assertEquals(request.getUserId(), comment.getUserId());
        }
    }

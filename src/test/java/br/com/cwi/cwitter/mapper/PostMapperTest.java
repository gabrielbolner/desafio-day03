package br.com.cwi.cwitter.mapper;

import br.com.cwi.cwitter.controller.request.AddPostRequest;
import br.com.cwi.cwitter.domain.Post;
import br.com.cwi.cwitter.domain.PostPrivacy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PostMapperTest {

    @Test
    @DisplayName("Deve converter um AddPostRequest sem imagem em um Post sem imagem")
    void deveConverterUmAddPostRequestSemImagemEmUmPostSemImagem() {
        LocalDateTime now = LocalDateTime.now();
        AddPostRequest request = AddPostRequest.builder()
                .writtenContent("conteúdo")
                .postPrivacy(PostPrivacy.PUBLIC)
                .userId(2L)
                .inclusionDate(now)
                .build();

        Post post = PostMapper.toResponse(request);

        assertEquals(request.getWrittenContent(), post.getWrittenContent());
        assertNull(post.getImageContent());
        assertEquals(request.getPostPrivacy(), post.getPostPrivacy());
    }
}

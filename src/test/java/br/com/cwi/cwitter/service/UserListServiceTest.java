package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserListServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserListService userListService;

    @Test
    @DisplayName("Deve Listar por Nome ou Email")
    public void deveListarPorNomeOuEmail() {
        String text = "john";
        Pageable pageable = PageRequest.of(0, 10);
        User user = new User();
        user.setFullName("Joao");
        user.setNickname("joaozinho");
        user.setEmail("joao@cwi.com.br");
        Page<User> page = new PageImpl<>(Arrays.asList(user), pageable, 1L);
        Mockito.when(userRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndActive(text, text, true, pageable)).thenReturn(page);

        Page<UserResponse> result = userListService.listByNameOrEmail(text, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Joao", result.getContent().get(0).getFullName());
        assertEquals("joao@cwi.com.br", result.getContent().get(0).getEmail());
    }
}
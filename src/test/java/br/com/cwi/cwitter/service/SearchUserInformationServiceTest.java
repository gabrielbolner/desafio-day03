package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.factories.SimpleFactory;
import br.com.cwi.cwitter.factories.UserFactory;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchUserInformationServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SearchUserInformationService searchUserInformationService;

    @Test
    @DisplayName("Deve buscar um usuário pelo ID")
    void deveBuscarUmUsuarioPeloId() {
        User user = UserFactory.get();
        Long id = user.getId();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = searchUserInformationService.searchUserById(id);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("Deve retornar exceção ao buscar usuário inexistente")
    void deveRetornarExcecaoAoBuscarUsuarioInexistente() {
        Long id = SimpleFactory.getRandomLong();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            searchUserInformationService.searchUserById(id);
        });
    }
}


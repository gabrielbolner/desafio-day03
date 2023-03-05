package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.domain.Friend;
import br.com.cwi.cwitter.repository.FriendsRepository;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.cwitter.domain.Status.REQUESTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FriendsSearchServiceTest {

    @Mock
    private FriendsRepository friendsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SearchUserInformationService searchUserInformationService;

    @InjectMocks
    private FriendsSearchService friendsSearchService;



    @Test
    @DisplayName("Deve Criar Pedido de Amizade")
    public void deveCriarPedidoDeAmizade() {
        Long userId = 1L;
        Long friendId1 = 2L;
        Long friendId2 = 3L;

        List<Friend> friendList = new ArrayList<>();
        friendList.add(new Friend(friendId1, userId, REQUESTED));
        friendList.add(new Friend(friendId2, userId, REQUESTED));

        User user1 = new User();
        user1.setFullName("Joao");
        User user2 = new User();
        user2.setEmail("jose@cwi.com");

        when(friendsRepository.findByFriendIdAndStatus(userId, REQUESTED)).thenReturn(friendList);
        when(searchUserInformationService.searchUserById(friendId1)).thenReturn(user1);
        when(searchUserInformationService.searchUserById(friendId2)).thenReturn(user2);

        List<UserResponse> result = friendsSearchService.getRequests(userId);

        assertEquals(2, result.size());
        assertEquals("Joao", result.get(0).getFullName());
        assertEquals("jose@cwi.com", result.get(1).getEmail());
    }
}
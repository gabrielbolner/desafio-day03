package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.domain.Friend;
import br.com.cwi.cwitter.repository.FriendsRepository;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.mapper.UserMapper;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.cwi.cwitter.domain.Status.ACCEPTED;
import static br.com.cwi.cwitter.domain.Status.REQUESTED;

@Service
public class FriendsSearchService {

    @Autowired
    private  FriendsRepository friendsRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  SearchUserInformationService searchUserInformationService;

    public FriendsSearchService(FriendsRepository friendsRepository, UserRepository userRepository,
                                SearchUserInformationService searchUserInformationService) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
        this.searchUserInformationService = searchUserInformationService;
    }

    public List<UserResponse> getAllFriends(Long id) {
        List<Friend> friendsList = friendsRepository.findByUserIdOrFriendIdAndStatus(id, id, ACCEPTED);

        friendsList = friendsList.stream().filter(friend -> friend.getStatus().equals(ACCEPTED)).collect(Collectors.toList());

        List<User> friends = friendsList.stream()
                .flatMap(friend -> Stream.of(friend.getUserId(), friend.getFriendId()))
                .distinct()
                .filter(friendId -> !friendId.equals(id))
                .map(searchUserInformationService::searchUserById)
                .collect(Collectors.toList());

        return friends.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getRequests(Long id) {
        List<Friend> friendList = friendsRepository.findByFriendIdAndStatus(id, REQUESTED);

        List<User> users = friendList.stream()
                .map(friend -> searchUserInformationService.searchUserById(friend.getUserId()))
                .collect(Collectors.toList());

        return users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void requestFriendship(Long userId, Long friendId) {
        Friend newRequest = new Friend(userId, friendId, REQUESTED);
        friendsRepository.save(newRequest);
    }

    public void acceptRequest(Long userId, Long friendId) {
        Friend friendshipRequest = friendsRepository.findByUserIdAndFriendIdAndStatus(friendId, userId, REQUESTED);
        friendshipRequest.setStatus(ACCEPTED);
        friendsRepository.save(friendshipRequest);
    }

    public void removeFriendship(Long userId, Long friendId) {
        Friend existingFriendship = friendsRepository.findByUserIdAndFriendIdAndStatus(userId, friendId, ACCEPTED);

        if (existingFriendship == null) {
            existingFriendship = friendsRepository.findByUserIdAndFriendIdAndStatus(friendId, userId, ACCEPTED);
        }

        if (existingFriendship != null) {
            friendsRepository.delete(existingFriendship);
        }
    }

    public List<UserResponse> getByNameOrEmail(Long id, String text) {
        List<Friend> friendsList = friendsRepository.findByUserIdOrFriendIdAndStatus(id, id, ACCEPTED);

        List<User> friends = friendsList.stream()
                .flatMap(friend -> Stream.of(friend.getUserId(), friend.getFriendId()))
                .distinct()
                .filter(friendId -> !friendId.equals(id))
                .map(searchUserInformationService::searchUserById)
                .filter(user -> text == null ||
                        user.getFullName().toLowerCase().contains(text.toLowerCase()) ||
                        user.getEmail().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());

        return friends.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

}
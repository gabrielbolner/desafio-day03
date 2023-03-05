package br.com.cwi.cwitter.controller;

import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.service.FriendsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsSearchService friendsSearchService;

    @GetMapping("/{id}/friends")
    public List<UserResponse> getAllFriends(@PathVariable Long id) {
        return friendsSearchService.getAllFriends(id);
    }

    @GetMapping("/{id}/requests")
    public List<UserResponse> getRequests(@PathVariable Long id) {
        return friendsSearchService.getRequests(id);
    }

    @PostMapping("/{userId}/request/{friendId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void requestFriendship(@PathVariable Long userId, @PathVariable Long friendId) {
        friendsSearchService.requestFriendship(userId, friendId);
    }

    @PostMapping("/{userId}/accept/{friendId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void acceptRequest(@PathVariable Long userId, @PathVariable Long friendId) {
        friendsSearchService.acceptRequest(userId, friendId);
    }

    @DeleteMapping("/{userId}/remove/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFriendship(@PathVariable Long userId, @PathVariable Long friendId) {
        friendsSearchService.removeFriendship(userId, friendId);
    }

    @GetMapping("/{id}/search")
    public List<UserResponse> getByNameOrEmail(@PathVariable Long id, @RequestParam(required = false) String text) {
        return friendsSearchService.getByNameOrEmail(id, text);
    }
}
package br.com.cwi.cwitter.controller;

import br.com.cwi.cwitter.controller.request.UpdateUserRequest;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserListService userListService;

    @GetMapping("/search")
    public Page<UserResponse> listBySearch(@RequestParam String text, Pageable pageable) {
        return userListService.listByNameOrEmail(text, pageable);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userListService.getById(id);
    }

    @PostMapping("/{id}/update")
    public UserResponse updateProfile(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return userListService.updateProfile(id,request);
    }
}

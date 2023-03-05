package br.com.cwi.cwitter.security.mapper;

import br.com.cwi.cwitter.security.controller.request.UserRequest;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        User entity = new User();
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UserResponse toResponse(User entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setFullName(entity.getFullName());
        response.setEmail(entity.getEmail());
        response.setBirthdate(entity.getBirthdate());
        response.setNickname(entity.getNickname());
        response.setProfileImage(entity.getProfileImage());
        return response;
    }
}

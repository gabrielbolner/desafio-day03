package br.com.cwi.cwitter.security.service;

import br.com.cwi.cwitter.security.controller.request.UserRequest;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.Permission;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.cwi.cwitter.security.domain.Function.USER;
import static br.com.cwi.cwitter.security.mapper.UserMapper.toEntity;
import static br.com.cwi.cwitter.security.mapper.UserMapper.toResponse;

@Service
public class AddUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse add(UserRequest request) {

        User user = toEntity(request);
        user.setPassword(getSenhaCriptografada(request.getPassword()));
        user.addPermission(getPermissaoPadrao());
        user.setBirthdate(request.getBirthdate());
        user.setNickname(request.getNickname());
        user.setProfileImage(request.getProfileImage());
        user.setActive(true);

        userRepository.save(user);

        return toResponse(user);
    }

    private String getSenhaCriptografada(String openPassword) {
        return passwordEncoder.encode(openPassword);
    }

    private Permission getPermissaoPadrao() {
        Permission permission = new Permission();
        permission.setFunction(USER);
        return permission;
    }
}

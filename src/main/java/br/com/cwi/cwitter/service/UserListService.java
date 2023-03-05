package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.controller.request.UpdateUserRequest;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.mapper.UserMapper;
import br.com.cwi.cwitter.security.repository.UserRepository;
import br.com.cwi.cwitter.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserListService {

    @Autowired
    private UserRepository userRepository;


    public Page<UserResponse> listByNameOrEmail(String received, Pageable pageable) {

        Page<UserResponse> map = userRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndActive(received, received, true, pageable)
                .map(UserMapper::toResponse);
        return map;
    }

    public UserResponse getById(Long id) {
        User foundUser = userRepository.findById(id).get();
        return UserMapper.toResponse(foundUser);
    }

    public UserResponse updateProfile(Long id, UpdateUserRequest request) {


        User user = UserValidator.findById(id);

        if(Objects.nonNull(request.getNickname())){
            user.setNickname(request.getNickname());
        }
        if(Objects.nonNull(request.getProfileImage())){
            user.setProfileImage(request.getProfileImage());
        }
        if(Objects.nonNull(request.getFullName())){
            user.setFullName(request.getFullName());
        }
        userRepository.save(user);

        return UserMapper.toResponse(user);
    }
}

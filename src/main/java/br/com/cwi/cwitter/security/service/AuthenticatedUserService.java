package br.com.cwi.cwitter.security.service;


import br.com.cwi.cwitter.security.config.UserSecurity;
import br.com.cwi.cwitter.security.controller.response.UserResponse;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static br.com.cwi.cwitter.security.mapper.UserMapper.toResponse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class AuthenticatedUserService {

    @Autowired
    private UserRepository userRepository;

    public Long getId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserSecurity) {
            return ((UserSecurity) authentication.getPrincipal()).getId();
        }

        return null;
    }

    public User get() {
        Long id = getId();

        if (isNull(id)) {
            return null;
        }

        return userRepository.findById(getId()).orElse(null);
    }

    public UserResponse getResponse() {
        User entity = get();
        return nonNull(entity) ? toResponse(entity) : new UserResponse();
    }
}

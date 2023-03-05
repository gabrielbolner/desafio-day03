package br.com.cwi.cwitter.validator;

import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserValidator {
    @Autowired
    private static UserRepository userRepository;


    public static User findById(Long id) {
        User user = userRepository.findById(id).get();
        if (Objects.isNull(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario não existe");
        }
        return user;
    }
}

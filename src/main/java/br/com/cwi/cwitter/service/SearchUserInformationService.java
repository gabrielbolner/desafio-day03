package br.com.cwi.cwitter.service;

import br.com.cwi.cwitter.repository.FriendsRepository;
import br.com.cwi.cwitter.security.domain.User;
import br.com.cwi.cwitter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchUserInformationService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;

    public User searchUserById(Long id) {
        return userRepository.findById(id).get();
    }
}

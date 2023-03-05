package br.com.cwi.cwitter.security.repository;

import br.com.cwi.cwitter.security.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Page<User> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndActive(String fullName,
                                                                                      String email,
                                                                                      boolean active,
                                                                                      Pageable pageable);

}

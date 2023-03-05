package br.com.cwi.cwitter.repository;

import br.com.cwi.cwitter.domain.Friend;
import br.com.cwi.cwitter.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByUserIdOrFriendIdAndStatus(Long userId, Long friendId, Status status);

    List<Friend> findByFriendIdAndStatus(Long friendId, Status status);

    Friend findByUserIdAndFriendIdAndStatus(Long userId, Long friendId, Status status);
}

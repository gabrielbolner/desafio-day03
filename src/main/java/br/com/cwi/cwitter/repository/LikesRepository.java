package br.com.cwi.cwitter.repository;

import br.com.cwi.cwitter.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByPostId(Long postId);

    Likes findByPostIdAndUserId(Long postId, Long userId);
}

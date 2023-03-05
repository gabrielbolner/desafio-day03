package br.com.cwi.cwitter.repository;

import br.com.cwi.cwitter.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);
}

package br.com.cwi.cwitter.repository;

import br.com.cwi.cwitter.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByUserIdInOrderByInclusionDateDesc(List<Long> friendsIds, Pageable pageable);
}




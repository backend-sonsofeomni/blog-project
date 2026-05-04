package io.backend.blogproject.repository;

import io.backend.blogproject.constant.Status;
import io.backend.blogproject.constant.Visibility;
import io.backend.blogproject.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByStatus(Status status);

    Optional<Post> findByPostIdAndStatus(Long postId, Status status);

    List<Post> findAllByStatusAndVisibility(Status status, Visibility visibility);
}

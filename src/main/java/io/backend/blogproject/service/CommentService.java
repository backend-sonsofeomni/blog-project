package io.backend.blogproject.service;

import io.backend.blogproject.constant.ErrorCode;
import io.backend.blogproject.domain.entity.Comment;
import io.backend.blogproject.domain.entity.Post;
import io.backend.blogproject.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long postId){
        return commentRepository.findCommentsByPostId(postId);
    }

}











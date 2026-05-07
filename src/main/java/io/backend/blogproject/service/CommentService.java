package io.backend.blogproject.service;

import io.backend.blogproject.constant.Status;
import io.backend.blogproject.constant.Visibility;
import io.backend.blogproject.domain.dto.CommentRequest;
import io.backend.blogproject.domain.dto.Page;
import io.backend.blogproject.domain.entity.Comment;
import io.backend.blogproject.domain.entity.Post;
import io.backend.blogproject.repository.CommentRepository;
import io.backend.blogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;



    public Page getComments(
            Long postId,
            int size,
            int page
    ){

        return commentRepository.findCommentsByPostId(
                postId,
                size,
                page
        );
    }

    public void createComment(
        Long postId,
        CommentRequest.Create request
    ){
        Optional<Post> foundedOptionalPost = postRepository
                .findByPostIdAndStatusAndVisibility(
                postId,
                Status.ACTIVATED,
                Visibility.PUBLIC
        );

        Post foundedPost = foundedOptionalPost.orElseThrow(() -> {
            throw new IllegalAccessError("게시글 조회에 실패했습니다.");
        });

        commentRepository.createComment(
                foundedPost,
                request.content()
        );
    }

    public void replyComment(
            Long postId,
            Long commentId,
            CommentRequest.Create request
    ){
        Optional<Post> foundedOptionalPost = postRepository
                .findByPostIdAndStatusAndVisibility(
                        postId,
                        Status.ACTIVATED,
                        Visibility.PUBLIC
                );

        Post foundedPost = foundedOptionalPost.orElseThrow(() -> {
            throw new IllegalAccessError("게시글 조회에 실패했습니다.");
        });

        Comment foundedComment = commentRepository.findCommentByCommentId(commentId);

        commentRepository.replyComment(
                foundedPost,
                foundedComment,
                request.content()
        );
    }

    public Comment getCommentById(Long commentId){
        return commentRepository.findCommentByCommentId(commentId);
    }

    public void deleteComment(Long commentId){
        Comment foundedComment = commentRepository.findCommentByCommentId(commentId);
        commentRepository.deleteComment(foundedComment);
    }
}











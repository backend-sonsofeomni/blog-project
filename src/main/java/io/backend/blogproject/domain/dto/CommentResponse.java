package io.backend.blogproject.domain.dto;

import io.backend.blogproject.domain.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
    Long commentId,
    String content,
    LocalDateTime createdAt,
    Comment childId,
    Comment parentId
)
{ }

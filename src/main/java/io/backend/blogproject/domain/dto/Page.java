package io.backend.blogproject.domain.dto;

import io.backend.blogproject.domain.entity.Comment;

import java.util.List;

public record Page(
        int size,
        int page,
        Long totalSize,
        int totalPageNum,
        List<Comment> comments
) {
}

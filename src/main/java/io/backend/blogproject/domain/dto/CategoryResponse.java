package io.backend.blogproject.domain.dto;

import io.backend.blogproject.domain.entity.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse {

    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }
}
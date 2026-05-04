package io.backend.blogproject.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryUpdateRequest {

    private String title;

    public CategoryUpdateRequest(String title) {
        this.title = title;
    }
}
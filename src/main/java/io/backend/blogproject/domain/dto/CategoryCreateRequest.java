package io.backend.blogproject.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateRequest {

    private String title;

    public CategoryCreateRequest(String title) {
        this.title = title;
    }
}
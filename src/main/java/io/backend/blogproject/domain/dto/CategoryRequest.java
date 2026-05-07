package io.backend.blogproject.domain.dto;

public class CategoryRequest {
    public record Create(
            String title
    ){ }

    public record Update(
            String title
    ){ }
}

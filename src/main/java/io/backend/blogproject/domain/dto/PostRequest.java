package io.backend.blogproject.domain.dto;

import io.backend.blogproject.constant.Visibility;

public class PostRequest {
    public record Create(
      String title,
      String content,
      Visibility visibility,
      Long categoryId
    ){}

    public record Update(
            String title,
            String content,
            Visibility visibility,
            Long categoryId
    ){}
}

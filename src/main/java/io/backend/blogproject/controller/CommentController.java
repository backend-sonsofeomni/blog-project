package io.backend.blogproject.controller;

import io.backend.blogproject.domain.dto.CommentRequest;
import io.backend.blogproject.domain.entity.Comment;
import io.backend.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{postId}/create")
    public String createComment(
        @PathVariable Long postId,
        @RequestBody CommentRequest.Create request
    ){
        commentService.createComment(
                postId,
                request
        );

        return "redirect:/posts/%d".formatted(postId);
    }

    @PostMapping("/comments/{commentId}/reply")
    public String replyComment(
            @PathVariable Long commentId,
            @RequestParam Long postId,
            @RequestBody CommentRequest.Create request
    ){
        commentService.replyComment(
                postId,
                commentId,
                request
        );

        return "redirect:/posts/%d".formatted(postId);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseBody
    public void deleteComment(
            @PathVariable Long commentId
    ){
        commentService.deleteComment(commentId);
    }

}

package io.backend.blogproject.controller;

import io.backend.blogproject.constant.Visibility;
import io.backend.blogproject.domain.dto.Page;
import io.backend.blogproject.domain.dto.PostRequest;
import io.backend.blogproject.domain.dto.PostResponse;
import io.backend.blogproject.service.CategoryService;
import io.backend.blogproject.service.CommentService;
import io.backend.blogproject.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    // 조회
    @GetMapping("/posts")
    public String list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "false") boolean noCategory,
            Model model
    ){
        PostResponse.PostPage response = postService.getPublicPosts(page, categoryId, noCategory);

        model.addAttribute("page", response);
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("noCategory", noCategory);
        model.addAttribute("posts", response.posts());

        return "posts";
    }

    //단건조회
    @GetMapping("/posts/{postId}")
    public String detail(
            @PathVariable Long postId,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
            ){

        String cookieName = "viewed_post_" + postId;

        Page foundedPage = commentService
                .getComments(
                        postId,
                        size,
                        page
                );


        model.addAttribute("comments",foundedPage.comments());

        boolean alreadyViewed = hasCookie(request, cookieName);

        if(alreadyViewed){
            model.addAttribute("post",postService.getPostWithoutViewCount(postId));
        }else{
            model.addAttribute("post", postService.getPost(postId));

            Cookie cookie = new Cookie(cookieName, "true");
            cookie.setMaxAge(60*5); // 5분
            cookie.setPath("/posts/"+postId);
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
        }

        return "post_detail";
    }

    // 생성
    @GetMapping("/posts/new")
    public String createForm(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "post_form";
    }

    @PostMapping("/posts")
    public String createPost(PostRequest.Create request){
        Long postId = postService.createPost(request);

        if(request.visibility() == Visibility.PRIVATE){
            return "redirect:/posts";
        }

        return "redirect:/posts/" + postId;
    }

    // 수정
    @GetMapping("/posts/{postId}/edit")
    public String updateForm(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPostForEdit(postId));
        model.addAttribute("categories", categoryService.getCategories());
        return "post_edit";
    }

    @PostMapping("posts/{postId}/edit")
    public String updateForm(
            @PathVariable Long postId,
            PostRequest.Update request
    ){
        postService.updatePost(postId, request);;

        if(request.visibility() == Visibility.PRIVATE){
            return "redirect:/posts";
        }

        return "redirect:/posts/"+postId;
    }


    // 삭제
    @PostMapping("/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }


    // 쿠키확인 메서드
    private boolean hasCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return false;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return true;
            }
        }

        return false;
    }
}

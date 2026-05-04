package io.backend.blogproject.service;

import io.backend.blogproject.constant.Status;
import io.backend.blogproject.constant.Visibility;
import io.backend.blogproject.domain.dto.PostCreateRequest;
import io.backend.blogproject.domain.dto.PostDetailResponse;
import io.backend.blogproject.domain.dto.PostListResponse;
import io.backend.blogproject.domain.dto.PostUpdateRequest;
import io.backend.blogproject.domain.entity.Category;
import io.backend.blogproject.domain.entity.Post;
import io.backend.blogproject.repository.CategoryRepository;
import io.backend.blogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createPost(PostCreateRequest request){
        Category category = findCategoryOrNull(request.getCategoryId());

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .visibility(request.getVisibility())
                .category(category)
                .build();

        Post savedPost = postRepository.save(post);

        return savedPost.getPostId();
    }

    public List<PostListResponse> getPublicPosts(){
        List<Post> posts = postRepository.findAllByStatusAndVisibility(
                Status.ACTIVATED,
                Visibility.PUBLIC
        );


        return posts.stream()
                .map(PostListResponse::from)
                .toList();
    }

    @Transactional
    public PostDetailResponse getPost(Long postId){
        Post post = postRepository.findByPostIdAndStatusAndVisibility(postId, Status.ACTIVATED, Visibility.PUBLIC)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));
        post.increaseViewCount();

        return PostDetailResponse.from(post);
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequest request){
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));


        Category category = findCategoryOrNull(request.getCategoryId());


        post.update(
                request.getTitle(),
                request.getContent(),
                request.getVisibility(),
                category
        );
    }

    @Transactional
    public void deletePost(Long postId){
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));

        post.remove();
    }

    private Category findCategoryOrNull(Long categoryId) {
        if (categoryId == null) {
            return null;
        }

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다. id=" + categoryId));
    }
}
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
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .visibility(request.getVisibility())
                .category(null)
                .build();

        Post savedPost = postRepository.save(post);

        return savedPost.getPostId();
    }

    public List<PostListResponse> getPosts(){
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
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));
        post.increaseViewCount();

        return PostDetailResponse.from(post);
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequest request){
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));


        Category category = null;

        if(request.getCategoryId() != null){
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(()->new IllegalArgumentException("카테고리를 찾을 수 없습니다. id=" + request.getCategoryId()));
        }


        post.update(
                request.getTitle(),
                request.getContent(),
                request.getVisibility(),
                category
        );
    }
}

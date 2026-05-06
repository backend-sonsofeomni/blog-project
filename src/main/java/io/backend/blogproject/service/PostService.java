package io.backend.blogproject.service;

import io.backend.blogproject.constant.Status;
import io.backend.blogproject.constant.Visibility;
import io.backend.blogproject.domain.dto.PostRequest;
import io.backend.blogproject.domain.dto.PostResponse;
import io.backend.blogproject.domain.entity.Category;
import io.backend.blogproject.domain.entity.Post;
import io.backend.blogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    //private final CategoryRepository categoryRepository;

    public Long createPost(PostRequest.Create request){
        Category category = findCategoryOrNull(request.categoryId());

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .visibility(request.visibility())
                .category(category)
                .build();

        Post savedPost = postRepository.save(post);

        return savedPost.getPostId();
    }

    public List<PostResponse.PostList> getPublicPosts(){
        List<Post> posts = postRepository.findAllByStatusAndVisibility(
                Status.ACTIVATED,
                Visibility.PUBLIC
        );


        return posts.stream()
                .map(PostResponse.PostList::from)
                .toList();
    }

    public PostResponse.PostDetail getPost(Long postId){
        Post post = postRepository.findByPostIdAndStatusAndVisibility(postId, Status.ACTIVATED, Visibility.PUBLIC)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));
        post.increaseViewCount();
        postRepository.update(post);

        return PostResponse.PostDetail.from(post);
    }

    public PostResponse.PostDetail getPostForEdit(Long postId) {
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + postId));

        return PostResponse.PostDetail.from(post);
    }

    public void updatePost(Long postId, PostRequest.Update request){
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));


        Category category = findCategoryOrNull(request.categoryId());


        post.update(
                request.title(),
                request.content(),
                request.visibility(),
                category
        );
        postRepository.update(post);
    }

    public void deletePost(Long postId){
        Post post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVATED)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다. id="+postId));

        post.remove();
        postRepository.update(post);
    }

    private Category findCategoryOrNull(Long categoryId) {
        if (categoryId == null) {
            return null;
        }

//        return categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다. id=" + categoryId));

        return null;
    }
}
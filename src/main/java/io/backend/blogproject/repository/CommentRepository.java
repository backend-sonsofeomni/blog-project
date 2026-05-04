package io.backend.blogproject.repository;

import io.backend.blogproject.constant.ErrorCode;
import io.backend.blogproject.domain.entity.Comment;
import io.backend.blogproject.domain.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManagerFactory emf;

    public List<Comment> findCommentsByPostId(Long postId){
        try(
                EntityManager em = emf.createEntityManager()
        ) {
            String JPQL_GET_COMMENTS = """
                    SELECT c
                    FROM Comment c
                    WHERE c.post = :postId
                    """;
            return em.createQuery(JPQL_GET_COMMENTS, Comment.class)
                    .setParameter("postId", postId)
                    .getResultList();
        } catch(Exception e) {
            throw new RuntimeException(ErrorCode.UNABLE_TO_FIND_COMMENT.message,e);
        }
    }

    public Comment findCommentByCommentId(Long commentId){
        try(
                EntityManager em = emf.createEntityManager()
        ) {
            String JPQL_GET_COMMENTS = """
                    SELECT c
                    FROM Comment c
                    WHERE c.id = :commentId
                    """;
            Comment foundedComment = em.createQuery(JPQL_GET_COMMENTS, Comment.class)
                    .setParameter("commentId", commentId)
                    .getSingleResult();

            if (foundedComment==null) throw new RuntimeException(ErrorCode.UNABLE_TO_FIND_COMMENT.message);
            return foundedComment;
        } catch(Exception e) {
            throw new RuntimeException(ErrorCode.UNABLE_TO_FIND_COMMENT.message,e);
        }
    }
}

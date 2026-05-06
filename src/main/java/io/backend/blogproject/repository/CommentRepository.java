package io.backend.blogproject.repository;

import io.backend.blogproject.constant.ErrorCode;
import io.backend.blogproject.constant.Status;
import io.backend.blogproject.domain.dto.Page;
import io.backend.blogproject.domain.entity.Comment;
import io.backend.blogproject.domain.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManagerFactory emf;

    public Page findCommentsByPostId(
            Long postId,
            int size,
            int page
    ){
        List<Comment> lst;
        Long totalNum;
        try(
                EntityManager em = emf.createEntityManager()
        ) {
            String JPQL_GET_COMMENTS = """
                    SELECT c
                    FROM Comment c
                    WHERE c.post.id = :postId
                    AND c.status != 'REMOVED'
                    """;

            String JPQL_GET_COUNTS = """
                    SELECT count(*)
                    FROM Comment c
                    WHERE c.post.id = :postId
                    AND c.status != 'REMOVED'
                    """;

            lst = em.createQuery(JPQL_GET_COMMENTS, Comment.class)
                    .setParameter("postId", postId)
                    .setFirstResult(size*page)
                    .setMaxResults(size)
                    .getResultList();

            totalNum = em.createQuery(JPQL_GET_COUNTS, Long.class)
                    .setParameter("postId", postId)
                    .getSingleResult();
        } catch(Exception e) {
            throw new RuntimeException(ErrorCode.UNABLE_TO_FIND_COMMENT.message,e);
        }

        int totalPageNum = (int)Math.ceil((double)totalNum/size);

        return new Page(
                size,
                page,
                totalNum,
                totalPageNum,
                lst
        );
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

            if (foundedComment.getStatus().equals(Status.REMOVED)) throw new RuntimeException(ErrorCode.ALREADY_DELETED.message);
            if (foundedComment==null) throw new RuntimeException(ErrorCode.UNABLE_TO_FIND_COMMENT.message);
            return foundedComment;
        } catch(Exception e) {
            throw new RuntimeException(ErrorCode.UNABLE_TO_FIND_COMMENT.message,e);
        }
    }

    public void createComment(
            Post post,
            String content
    ){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Post managed  = em.merge(post);

            Comment comment = Comment.createComment(
                    managed,
                    content
            );

            em.persist(comment);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            throw new RuntimeException(ErrorCode.NO_POST.message,e);
        } finally {
            em.close();
        }
    }

    public void replyComment(
            Post post,
            Comment parentComment,
            String content
    ){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Post managedPost  = em.merge(post);
            Comment managedComment = em.merge(parentComment);

            Comment comment = Comment.replyComment(
                    managedPost,
                    content,
                    managedComment
            );

            em.persist(comment);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            throw new RuntimeException(ErrorCode.CAN_NOT_CREATE_COMMENT.message,e);
        } finally {
            em.close();
        }
    }

    public void deleteComment(Comment comment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            comment.delete();
            em.merge(comment);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            throw new RuntimeException(ErrorCode.CAN_NOT_CREATE_COMMENT.message,e);
        } finally {
            em.close();
        }
    }
}

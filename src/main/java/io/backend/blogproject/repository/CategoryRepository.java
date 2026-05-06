package io.backend.blogproject.repository;

import io.backend.blogproject.constant.Status;
import io.backend.blogproject.domain.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManagerFactory emf;

    public Optional<Category> findById(Long categoryId) {

        try (EntityManager em = emf.createEntityManager()) {

            String jpql = """
                    SELECT c
                    FROM Category c
                    WHERE c.id = :categoryId
                    AND c.status = :status
                    """;

            List<Category> result = em.createQuery(jpql, Category.class)

                    .setParameter("categoryId", categoryId)
                    .setParameter("status", Status.ACTIVATED)
                    .getResultList();

            return result.stream().findFirst();

        } catch (Exception e) {

            throw new RuntimeException("카테고리 조회에 실패!", e);

        }
    }
}
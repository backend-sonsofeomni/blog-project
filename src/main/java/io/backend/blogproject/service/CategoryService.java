package io.backend.blogproject.service;

import io.backend.blogproject.domain.dto.CategoryRequest;
import io.backend.blogproject.domain.entity.Category;
import io.backend.blogproject.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAllActivated();
    }

       public void createCategory(CategoryRequest.Create request) {
        if (request.title() == null || request.title().isBlank()) {
            throw new IllegalArgumentException("카테고리 이름을 작성해주세용.");
        }

        Category category = new Category(request.title());
        categoryRepository.save(category);
    }

    public void updateCategory(Long categoryId, CategoryRequest.Update request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니데."));

        category.update(request.title());

        categoryRepository.update(category);
    }


    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        category.softDelete();

        categoryRepository.update(category);
    }
}
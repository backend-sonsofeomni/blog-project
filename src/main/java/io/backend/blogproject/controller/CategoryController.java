package io.backend.blogproject.controller;

import io.backend.blogproject.domain.dto.CategoryRequest;
import io.backend.blogproject.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "categories/list";
    }

    @PostMapping
    public String create(CategoryRequest.Create request) {
        categoryService.createCategory(request);
        return "redirect:/categories";
    }

    @PutMapping("/{categoryId}")
    @ResponseBody
    public ResponseEntity<String> update(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest.Update request
    ) {
        categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok("정상처리 되었습니다.");
    }

    @DeleteMapping("/{categoryId}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("정상처리 되었습니다.");
    }
}
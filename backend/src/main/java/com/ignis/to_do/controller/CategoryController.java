package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.model.Category;
import com.ignis.to_do.service.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/category")
@Tag(name = "Category Controller", description = "Gerenciamento de categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/createCategory/{name}")
    public Category createCategory(@PathVariable String name) {
        Category category = new Category(name);
        categoryService.createCategory(category);
        return category;
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {        
        return categoryService.getCategory(id);
    }

    @GetMapping("/getAllCategories")
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/updateCategoryName/{id}/{name}")
    public Category updateCategory(
        @PathVariable Long id,
        @PathVariable String name) {
            
        return categoryService.updateCategory(id, name);
    }
}

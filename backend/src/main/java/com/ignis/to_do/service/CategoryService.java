package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.model.Category;
import com.ignis.to_do.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }   
    
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).get();
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    @Transactional
    public Category updateCategory(Long id, String name) {
        categoryRepository.updateCategoryName(id, name);
        return categoryRepository.findById(id).get();
    }
}
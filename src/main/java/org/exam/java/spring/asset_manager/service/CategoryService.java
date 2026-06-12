package org.exam.java.spring.asset_manager.service;

import java.util.List;
import java.util.Optional;

import org.exam.java.spring.asset_manager.model.Category;
import org.exam.java.spring.asset_manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // READ
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllSorted(String property, Sort.Direction direction) {
        return categoryRepository.findAll(Sort.by(direction, property));
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    public Category findById(Integer id) {
        Optional<Category> categoryAttempt = categoryRepository.findById(id);
        if (categoryAttempt.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        return categoryAttempt.get();
    }

    // CREATE
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    // UPDATE
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}

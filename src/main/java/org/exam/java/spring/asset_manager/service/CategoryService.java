package org.exam.java.spring.asset_manager.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.model.Category;
import org.exam.java.spring.asset_manager.repository.AssetRepository;
import org.exam.java.spring.asset_manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AssetRepository assetRepository;

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

    // ***
    public List<Category> findAllOrderByRiskLevelAsc() {
        return categoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(c -> c.getRiskLevel().getOrder()))
                .collect(Collectors.toList());
    }

    public List<Category> findAllOrderByRiskLevelDesc() {
        return categoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt((Category c) -> c.getRiskLevel().getOrder()).reversed())
                .collect(Collectors.toList());
    }
    // ***

    // *** */
    public List<Category> findAllOrderByAssetsCountAsc() {
        return categoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(c -> c.getAssets().size()))
                .collect(Collectors.toList());
    }

    public List<Category> findAllOrderByAssetsCountDesc() {
        return categoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt((Category c) -> c.getAssets().size()).reversed())
                .collect(Collectors.toList());
    }

    // *** */
    // CREATE
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    // DELETE
    public void deleteById(Integer categoryId) {

        List<Asset> assets = assetRepository.findByCategoryId(categoryId);

        for (Asset asset : assets) {
            asset.setCategory(null);
        }

        categoryRepository.deleteById(categoryId);
    }
}

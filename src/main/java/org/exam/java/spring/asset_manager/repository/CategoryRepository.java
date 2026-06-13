package org.exam.java.spring.asset_manager.repository;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String name);

}

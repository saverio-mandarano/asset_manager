package org.exam.java.spring.asset_manager.repository;

import java.util.List;

import org.exam.java.spring.asset_manager.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findByNameContainingIgnoreCase(String name);

}
package org.exam.java.spring.asset_manager.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.model.Tag;
import org.exam.java.spring.asset_manager.repository.AssetRepository;
import org.exam.java.spring.asset_manager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private AssetRepository assetRepository;

    // READ
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public List<Tag> findAllSorted(String property, Sort.Direction direction) {
        return tagRepository.findAll(Sort.by(direction, property));
    }

    public List<Tag> findByName(String name) {
        return tagRepository.findByNameContainingIgnoreCase(name);
    }

    public Tag findById(Integer id) {
        Optional<Tag> tagAttempt = tagRepository.findById(id);
        if (tagAttempt.isEmpty()) {
            throw new RuntimeException("Tag not found with id: " + id);
        }
        return tagAttempt.get();
    }

    public Optional<Tag> findByIdOptional(Integer id) {
        return tagRepository.findById(id);
    }

    public List<Tag> findAllOrderByAssetsCountAsc() {
        return tagRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(c -> c.getAssets().size()))
                .collect(Collectors.toList());
    }

    public List<Tag> findAllOrderByAssetsCountDesc() {
        return tagRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt((Tag c) -> c.getAssets().size()).reversed())
                .collect(Collectors.toList());
    }

    public List<Tag> findAllOrderByTypeAsc() {
        return tagRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(c -> c.getAssets().size()))
                .collect(Collectors.toList());
    }

    public List<Tag> findAllOrderByTypeDesc() {
        return tagRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt((Tag c) -> c.getAssets().size()).reversed())
                .collect(Collectors.toList());
    }

    // CREATE
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Tag tag) {
        return tagRepository.save(tag);
    }

    // DELETE
    public void deleteById(Integer tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tagId));

        for (Asset asset : tag.getAssets()) {
            if (asset.getTags() != null) {
                asset.getTags().remove(tag);
            }
        }

        tagRepository.deleteById(tagId);
    }
}

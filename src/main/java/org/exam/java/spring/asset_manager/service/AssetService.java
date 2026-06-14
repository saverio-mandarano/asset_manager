package org.exam.java.spring.asset_manager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.exam.java.spring.asset_manager.model.Asset;
import org.exam.java.spring.asset_manager.model.Category;
import org.exam.java.spring.asset_manager.model.Tag;
import org.exam.java.spring.asset_manager.repository.AssetRepository;
import org.exam.java.spring.asset_manager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private TagRepository tagRepository;

    // READ
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public List<Asset> findAllSorted(String property, Sort.Direction direction) {
        return assetRepository.findAll(Sort.by(direction, property));
    }

    public List<Asset> findAllSortedByPrice() {
        return assetRepository.findAll(Sort.by("lastPrice"));
    }

    public List<Asset> findAllSortedByName() {
        return assetRepository.findAll(Sort.by("name"));
    }

    public List<Asset> findAllSortedByTicker() {
        return assetRepository.findAll(Sort.by("ticker"));
    }

    // public Optional<Asset> findById(Integer id) {
    // return assetRepository.findById(id);
    // }

    // public Asset getById(Integer id) {
    // Optional<Asset> assetAttempt = assetRepository.findById(id);

    // if (assetAttempt.isEmpty()) {
    // // lanciare not found con una response entity
    // }

    // return assetAttempt.get();
    // }

    public Asset findById(Integer id) {
        Optional<Asset> assetAttempt = assetRepository.findById(id);

        if (assetAttempt.isEmpty()) {
            throw new RuntimeException("Asset not found with id: " + id);
        }

        return assetAttempt.get();
    }

    public List<Asset> findByCategory(Integer categoryId) {
        return assetRepository.findByCategoryId(categoryId);
    }

    public List<Asset> findByName(String name) {
        return assetRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Asset> findByTicker(String ticker) {
        return assetRepository.findByTickerContainingIgnoreCase(ticker);
    }

    public List<Asset> findByPriceGreater(BigDecimal lastPrice) {
        return assetRepository.findByLastPriceGreaterThanEqual(lastPrice);
    }

    public List<Asset> findByPriceLess(BigDecimal lastPrice) {
        return assetRepository.findByLastPriceLessThanEqual(lastPrice);
    }

    public List<Asset> findByTickerOrName(String query) {
        return assetRepository.findByTickerContainingIgnoreCaseOrNameContainingIgnoreCase(query,
                query);
    }

    // CREATE
    public Asset create(Asset asset) {
        return assetRepository.save(asset);
    }

    // // UPDATE
    public Asset update(Asset asset) {
        return assetRepository.save(asset);
    }

    //
    public void detachFromCategory(Integer assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow();

        asset.setCategory(null);
        assetRepository.save(asset);
    }

    public void detachTag(Integer assetId, Integer tagId) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow();

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow();

        asset.getTags().remove(tag);

        assetRepository.save(asset);
    }

    // DELETE
    public void deleteById(Integer assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));

        // rimuovo la relazione con la category (one to many)
        if (asset.getCategory() != null) {
            asset.getCategory().getAssets().remove(asset);
            asset.setCategory(null);
        }

        // rimuovo le relazioni con i tags (many to many)
        if (asset.getTags() != null) {
            for (Tag tag : asset.getTags()) {
                tag.getAssets().remove(asset);
            }
            asset.getTags().clear();
        }

        assetRepository.deleteById(assetId);
    }

}

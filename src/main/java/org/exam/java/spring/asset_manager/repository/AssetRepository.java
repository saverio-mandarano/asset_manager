package org.exam.java.spring.asset_manager.repository;

import java.math.BigDecimal;
import java.util.List;

import org.exam.java.spring.asset_manager.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
    public List<Asset> findByNameContainingIgnoreCase(String name);

    public List<Asset> findByTickerContainingIgnoreCase(String ticker);

    public List<Asset> findByTickerContainingIgnoreCaseOrNameContainingIgnoreCase(String ticker, String name);

    public List<Asset> findByLastPriceGreaterThanEqual(BigDecimal lastPrice);

    List<Asset> findByLastPriceLessThanEqual(BigDecimal lastPrice);
}

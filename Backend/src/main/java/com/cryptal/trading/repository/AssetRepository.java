package com.cryptal.trading.repository;

import com.cryptal.trading.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    public List<Asset> findByUserId(Long userId);

    Asset findByUserIdAndCoinId(Long userId, String coinId);

    Asset findByIdAndUserId(Long assetId, Long userId);


}

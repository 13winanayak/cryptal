package com.cryptal.trading.service;

import com.cryptal.trading.model.Asset;
import com.cryptal.trading.model.Coin;
import com.cryptal.trading.model.User;
import com.cryptal.trading.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImp implements AssetService{

    private final AssetRepository assetRepository;

    public AssetServiceImp(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(User user, Coin coin, double quantity) {
        Asset asset = new Asset();

        asset.setQuantity(quantity);
        asset.setBuyPrice(coin.getCurrentPrice());
        asset.setCoin(coin);
        asset.setUser(user);

        return assetRepository.save(asset);
    }


//    public Asset buyAsset(User user, Coin coin, Long quantity) {
//        return createAsset(user,coin,quantity);
//    }

    public Asset getAssetById(Long assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));
    }

    @Override
    public Asset getAssetByUserAndId(Long userId, Long assetId) {
        return assetRepository.findByIdAndUserId(assetId,userId);
    }

    @Override
    public List<Asset> getUsersAssets(Long userId) {
        return assetRepository.findByUserId(userId);
    }



    @Override
    public Asset updateAsset(Long assetId, double quantity) throws Exception {

        Asset oldAsset=getAssetById(assetId);
        if(oldAsset==null){
            throw new Exception("Asset not found...");
        }
        oldAsset.setQuantity(quantity+ oldAsset.getQuantity());

        return assetRepository.save(oldAsset);
    }

    @Override
    public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) throws Exception {
        return assetRepository.findByUserIdAndCoinId(userId,coinId);
    }


    public void deleteAsset(Long assetId) {
        assetRepository.deleteById(assetId);
    }

}

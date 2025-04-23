package com.mdm.project.service;


import com.mdm.project.dto.ShopDto;
import com.mdm.project.entity.ShopEntity;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.repository.ShopCollectionRepository;
import com.mdm.project.request.ShopRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShopService {

    private final ShopCollectionRepository shopRepository;
    private final RedisIdGenerator idGenerator;
    public ShopService(ShopCollectionRepository shopRepository, RedisIdGenerator idGenerator) {
        this.shopRepository = shopRepository;
        this.idGenerator = idGenerator;
    }

    public void insertShop(ShopRegisterRequest request) {
        ShopEntity shopEntity = new ShopEntity();
//        shopEntity.setShopId(UUID.randomUUID().toString());
        shopEntity.setShopId(idGenerator.getNextIdWithPrefix("shop", "S"));
        shopEntity.setShopName(request.name());
        shopEntity.setShopAddresses(request.shopAddress());
        shopEntity.setShopEmail(request.email());
        shopEntity.setShopPhoneNumber(request.mobileNumber());
        shopEntity.setShopShipMethods(request.shipMethodList());
        shopEntity.setBusinessAddress(request.businessAddress());
        shopEntity.setUserId(request.ownerId());

        shopRepository.save(shopEntity);
    }

    public ShopDto findByOwnerId(String userId) {
        ShopEntity shopEntity = shopRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("Shop", "owner id", userId)
        );

        ShopDto shopDto = new ShopDto();
        shopDto.setShopId(shopEntity.getShopId());
        shopDto.setShopName(shopEntity.getShopName());

        return shopDto;

    }

}

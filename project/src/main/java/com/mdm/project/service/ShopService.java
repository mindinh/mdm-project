package com.mdm.project.service;


import com.mdm.project.entity.ShopEntity;
import com.mdm.project.repository.ShopCollectionRepository;
import com.mdm.project.request.ShopRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private ShopCollectionRepository shopRepository;
    public ShopService(ShopCollectionRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void insertShop(ShopRegisterRequest request) {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setShopName(request.name());
        shopEntity.setShopAddresses(request.address());
        shopEntity.setShopEmail(request.email());
        shopEntity.setShopPhoneNumber(request.mobileNumber());
        shopEntity.setShopShipMethods(request.shipMethodList());

        shopRepository.save(shopEntity);
    }

}

package com.mdm.project.repository;


import com.mdm.project.dto.ShopDto;
import com.mdm.project.entity.ShopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopCollectionRepository extends MongoRepository<ShopEntity, String> {
    Optional<ShopEntity> findByUserId(String userId);

}

package com.mdm.project.repository;


import com.mdm.project.entity.ShopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCollectionRepository extends MongoRepository<ShopEntity, String> {
}

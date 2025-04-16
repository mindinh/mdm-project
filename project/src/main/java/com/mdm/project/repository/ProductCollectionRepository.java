package com.mdm.project.repository;

import com.mdm.project.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCollectionRepository extends MongoRepository<ProductEntity, String> {
}

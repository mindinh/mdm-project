package com.mdm.project.repository;

import com.mdm.project.dto.ProductDto;
import com.mdm.project.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCollectionRepository extends MongoRepository<ProductEntity, String> {
    List<ProductEntity> findByProductNameContainingIgnoreCase(String keyword);
    Optional<ProductEntity> findByProductId(String productId);
}

package com.mdm.project.repository;

import com.mdm.project.dto.ProductDto;
import com.mdm.project.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCollectionRepository extends MongoRepository<ProductEntity, String> {
    List<ProductEntity> findByProductNameContainingIgnoreCase(String keyword);
}

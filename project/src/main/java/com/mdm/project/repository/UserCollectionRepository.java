package com.mdm.project.repository;

import com.mdm.project.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCollectionRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByCustomerId(String id);
    Optional<UserEntity> findByUsername(String username);
    void deleteByCustomerId(String customerId);

    Optional<UserEntity> findByCustomerPhone(String s);
}

package com.mdm.project.repository;

import com.mdm.project.entity.UserCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCollectionRepository extends MongoRepository<UserCollection, String> {

    Optional<UserCollection> findByUsername(String username);
    Optional<UserCollection> findByCustomerId(String id);

    void deleteByCustomerId(String customerId);
}

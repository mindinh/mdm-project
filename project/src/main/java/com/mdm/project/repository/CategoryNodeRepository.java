package com.mdm.project.repository;


import com.mdm.project.entity.CategoryNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryNodeRepository extends Neo4jRepository<CategoryNode, String> {
    Optional<CategoryNode> findByCode(String code);
}

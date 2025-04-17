package com.mdm.project.repository;

import com.mdm.project.entity.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNodeRepository extends Neo4jRepository<ProductNode, String> {
}

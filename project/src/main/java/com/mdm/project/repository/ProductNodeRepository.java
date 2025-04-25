package com.mdm.project.repository;

import com.mdm.project.entity.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductNodeRepository extends Neo4jRepository<ProductNode, String> {
    @Query("""
        MATCH (p:Product {id: $id})-[:BELONGS_TO]->(c:Category)
        MATCH path=(c)-[:CHILD_OF*0..2]->(parent:Category)
        MATCH (relatedCategory:Category)-[:CHILD_OF*0..2]->(parent)
        MATCH (related:Product)-[:BELONGS_TO]->(relatedCategory)
        WHERE related.id <> $id
        RETURN DISTINCT related
        LIMIT 5
        """)
    List<ProductNode> findRelatedProductsByHierarchy(String id);
}

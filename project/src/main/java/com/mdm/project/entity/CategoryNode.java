package com.mdm.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Category")
@Data
@AllArgsConstructor @NoArgsConstructor
public class CategoryNode {

    @Id
    private String code;
    private String name;

    @Relationship(type = "CHILD_OF", direction = Relationship.Direction.OUTGOING)
    private CategoryNode parent;

}

package com.mdm.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String productId;
    private String productName;
    private String productDescription;
    private List<Category> productCategories;
    private String brand;
    private List<ProductVariant> productVariants;
    private String productImg;
    private ShopEntity shop;

}

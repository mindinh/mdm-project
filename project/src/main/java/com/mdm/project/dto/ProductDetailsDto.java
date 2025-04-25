package com.mdm.project.dto;


import com.mdm.project.entity.Category;
import com.mdm.project.entity.ProductVariant;
import com.mdm.project.entity.ShopEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDto {

    private String id;
    private String name;
    private String description;
    private List<Category> category;
    private String brand;
    private List<ProductVariant> variants;
    private String image;
    private String shopName;
    private List<ProductDto> relatedProducts;

}

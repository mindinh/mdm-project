package com.mdm.project.mapper;

import com.mdm.project.dto.ProductDto;
import com.mdm.project.entity.ProductEntity;

public class ProductMapper {
    public static ProductDto mapToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getProductId());
        productDto.setProdName(productEntity.getProductName());
        productDto.setProdPrice(!productEntity.getProductVariants().isEmpty() ? productEntity.getProductVariants().getFirst().getPrice() : productDto.getProdPrice());
        productDto.setProdImg("http://localhost:8080" + productEntity.getProductImg());
        return productDto;
    }

}

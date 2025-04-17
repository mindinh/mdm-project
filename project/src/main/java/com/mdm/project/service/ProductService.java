package com.mdm.project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdm.project.dto.ProductDto;
import com.mdm.project.entity.Category;
import com.mdm.project.entity.ProductEntity;
import com.mdm.project.entity.ProductVariant;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.mapper.ProductMapper;
import com.mdm.project.repository.ProductCollectionRepository;
import com.mdm.project.request.ProductInsertRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ObjectMapper objectMapper;
    private final FileService fileService;
    private final ProductCollectionRepository productRepository;
    public ProductService(ProductCollectionRepository productRepository, FileService fileService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    public List<ProductDto> findAllProducts(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString("asc"), "id"));
        return productRepository.findAll(pageable).stream().map(ProductMapper::mapToDto).toList();
    }

    public void insertProduct(ProductInsertRequest request) {

        try {
            List<Category> categories = objectMapper.readValue(request.getProductCategories(), new TypeReference<>() {
            });
            List<ProductVariant> variants = objectMapper.readValue(request.getProductVariants(), new TypeReference<>() {
            });

            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductId(UUID.randomUUID().toString());
            productEntity.setProductName(request.getProductName());
            productEntity.setProductDescription(request.getProductDescription());
            productEntity.setBrand(request.getBrand());
            productEntity.setProductCategories(categories);
            productEntity.setProductVariants(variants);

            String filePath = fileService.copyFile(request.getFile());
            productEntity.setProductImg(filePath);

            productRepository.save(productEntity);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<ProductDto> findByProductName(String productName) {
        List<ProductEntity> productList = productRepository.findByProductNameContainingIgnoreCase(productName);
        if (productList.isEmpty()) {
            throw new ResourceNotFoundException("Product", "product name", productName);
        }

        return productList.stream().map(ProductMapper::mapToDto).toList();
    }

}

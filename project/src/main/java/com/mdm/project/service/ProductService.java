package com.mdm.project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdm.project.dto.ProductDto;
import com.mdm.project.entity.*;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.mapper.ProductMapper;
import com.mdm.project.repository.CategoryNodeRepository;
import com.mdm.project.repository.ProductCollectionRepository;
import com.mdm.project.repository.ProductNodeRepository;
import com.mdm.project.request.ProductInsertRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ObjectMapper objectMapper;
    private final FileService fileService;
    private final ProductCollectionRepository productRepository;
    private final ProductNodeRepository productNodeRepository;
    private final CategoryNodeRepository categoryNodeRepository;
    public ProductService(ProductCollectionRepository productRepository, FileService fileService, ObjectMapper objectMapper, ProductNodeRepository productNodeRepository, CategoryNodeRepository categoryNodeRepository) {
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.productNodeRepository = productNodeRepository;
        this.categoryNodeRepository = categoryNodeRepository;
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

            ProductNode productNode = new ProductNode();
            productNode.setId(productEntity.getProductId());
            productNode.setName(request.getProductName());
            for (Category category : categories) {
                Optional<CategoryNode> node = categoryNodeRepository.findByCode(category.getId());
                if (node.isEmpty()) {
                    throw new ResourceNotFoundException("Category", "code", category.getId());
                }
                CategoryNode categoryNode = new CategoryNode();
                categoryNode.setCode(category.getId());
                categoryNode.setName(category.getName());

                productNode.setCategory(categoryNode);
            }

            productNodeRepository.save(productNode);


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

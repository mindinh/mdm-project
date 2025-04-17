package com.mdm.project.controller;


import com.mdm.project.dto.ProductDto;
import com.mdm.project.request.ProductInsertRequest;
import com.mdm.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertAProduct(@Valid @ModelAttribute ProductInsertRequest request) {
        productService.insertProduct(request);
        return ResponseEntity.ok("Product inserted");
    }

    @GetMapping("/all-products")
    public ResponseEntity<?> getAllProducts(@RequestParam int pageSize, @RequestParam int pageNumber) {

        return ResponseEntity.ok(productService.findAllProducts(pageSize, pageNumber));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByProductName(name));
    }

}

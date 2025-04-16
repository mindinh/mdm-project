package com.mdm.project.request;

import com.mdm.project.entity.Category;
import com.mdm.project.entity.ProductVariant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductInsertRequest {
    @NotNull(message = "1 product image must be included")
    private MultipartFile file;

    @NotBlank(message = "Product name must be included")
    private String productName;

    @NotBlank(message = "Product description must be included")
    private String productDescription;

    @NotBlank(message = "Product categories must be included")
    private String productCategories;

    @NotBlank(message = "Product brand must be included")
    private String brand;

    private String productVariants;

}

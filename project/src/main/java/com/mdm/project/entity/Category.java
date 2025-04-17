package com.mdm.project.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Category {
    private String id;
    @NotBlank(message = "Category name must be included")
    private String name;

}

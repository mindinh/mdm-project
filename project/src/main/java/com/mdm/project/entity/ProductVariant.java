package com.mdm.project.entity;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductVariant {
    private int id;
    private String name;
    private String size;
    private String color;
    @Min(value = 1, message = "Quantity must be more than 1")
    private int quantity;
    @Min(value = 1, message = "Price must be higher than 1")
    private double price;
}

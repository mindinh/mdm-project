package com.mdm.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  @NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private String productSKU;
    private int quantity;
    private double price;


}

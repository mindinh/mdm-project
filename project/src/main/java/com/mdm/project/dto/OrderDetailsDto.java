package com.mdm.project.dto;


import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.CartItem;
import com.mdm.project.entity.ShipMethod;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsDto {

    private String id;
    private String userId;
    private String orderTime;
    private String shopName;
    private List<CartItem> products;
    private double totalPrice;
    private String status;
    private ShipMethod shipMethod;
    private String paymentMethod;
    private AddressEntity shipAddress;


}

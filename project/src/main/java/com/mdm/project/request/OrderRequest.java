package com.mdm.project.request;


import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.ProductEntity;
import com.mdm.project.entity.ShipMethod;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String userId;
    private AddressEntity address;
    private List<ProductEntity> products;
    private ShipMethod shipMethod;
    private String paymentMethod;
    private Double totalPrice;


}

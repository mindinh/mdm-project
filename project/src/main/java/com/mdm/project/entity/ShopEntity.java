package com.mdm.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shops")
@Data
@AllArgsConstructor @NoArgsConstructor
public class ShopEntity {
    @Id
    private String id;
    private String shopId;
    private String shopName;
    private String shopEmail;
    private AddressEntity shopAddresses;
    private String shopPhoneNumber;
    private List<ShipMethod> shopShipMethods;
    private String businessType;
    private AddressEntity businessAddress;
    private List<String> receiptEmails;
    private String taxNumber;
    private String userId;
}

package com.mdm.project.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.util.List;


@Document(collection = "users")
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerGender;
    private String password;
    private String customerDOB;
    private String customerAvatar;
    @Indexed(unique = true)
    private String username;
    private List<AddressEntity> customerAddress;
    private List<CreditCardEntity> customerCards;
}

package com.mdm.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String customerDOB;
    private String customerAvatar;
    private List<AddressEntity> customerAddress;
    private List<CreditCardEntity> customerCards;


}

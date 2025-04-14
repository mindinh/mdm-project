package com.mdm.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreditCardEntity {
    private String cardNumber;
    private String bank;
    private String code;
    private String expiredIn;

}

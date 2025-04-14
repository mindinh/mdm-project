package com.mdm.project.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.CreditCardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserCollectionDto {
    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private String DOB;
    private String avatar;
    private List<AddressEntity> addresses;
    private List<CreditCardEntity> cards;

}

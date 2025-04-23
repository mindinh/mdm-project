package com.mdm.project.dto;


import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.CreditCardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserCollectionDto {
    private String id;
    private String email;
    private String name;
    private String username;
    private String phoneNumber;
    private String gender;
    private String DOB;
    private String avatar;
    private List<AddressEntity> addresses;
    private List<CreditCardEntity> cards;

}

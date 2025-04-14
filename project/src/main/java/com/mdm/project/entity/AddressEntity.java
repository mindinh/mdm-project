package com.mdm.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AddressEntity {
    private String houseNumber;
    private String street;
    private String city;
    private String ward;
    private String district;
}

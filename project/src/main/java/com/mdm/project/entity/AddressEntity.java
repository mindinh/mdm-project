package com.mdm.project.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AddressEntity {
    @NotBlank(message = "House Number must not be blank")
    private String houseNumber;
    @NotBlank(message = "Street must not be blank")
    private String street;
    @NotBlank(message = "City must not be blank")
    private String city;
    @NotBlank(message = "Ward must not be blank")
    private String ward;
    @NotBlank(message = "District must not be blank")
    private String district;
}

package com.mdm.project.request;

import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.ShipMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ShopRegisterRequest (
        @NotBlank(message = "Shop Name must not be blank")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{10,30}$",
                message = "Shop name must be 10-30 characters long"
        )
        String name,
        @Valid
        AddressEntity address,
        @NotBlank(message = "Email must not be blank")
        String email,
        @NotBlank(message = "Mobile number must not be blank")
        String mobileNumber,
        List<ShipMethod> shipMethodList

) {}

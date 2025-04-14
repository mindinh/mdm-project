package com.mdm.project.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest (

        @NotBlank(message = "Phone number must not be blank") @NotNull(message = "Phone number must not be null")
        String phoneNumber,

        @NotBlank(message = "Password must not be blank") @NotNull(message = "Password must not be null")
        String password
) {}

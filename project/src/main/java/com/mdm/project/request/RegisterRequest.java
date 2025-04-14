package com.mdm.project.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record RegisterRequest (

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    String phoneNumber,

    @NotBlank(message = "Password can not be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9@#$%^&+=]{8,16}$",
            message = "Password must be 8-16 characters long, include at least one uppercase and one lowercase letter, and only contain letters, digits, and @#$%^&+="
    )
    String password
) {}

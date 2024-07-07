package com.akerke.ecommerceapi.security.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String password,
        @NotBlank
        String name,
        @NotBlank
        String surname
){
}

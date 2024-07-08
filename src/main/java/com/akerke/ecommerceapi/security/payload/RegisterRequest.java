package com.akerke.ecommerceapi.security.payload;

import com.akerke.ecommerceapi.core.annotation.Password;
import com.akerke.ecommerceapi.core.annotation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @Email
        String email,
        @PhoneNumber
        String phone,
        @Password
        String password,
        @NotBlank
        String name,
        @NotBlank
        String surname
){
}

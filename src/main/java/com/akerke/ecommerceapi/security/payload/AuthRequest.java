package com.akerke.ecommerceapi.security.payload;

import com.akerke.ecommerceapi.core.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @Email
        String email,
        @Password
        String password
){}
package com.akerke.ecommerceapi.security.payload;

import com.akerke.ecommerceapi.core.annotation.Password;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @Password
        String password,
        @Password
        String confirmPassword
) {
}

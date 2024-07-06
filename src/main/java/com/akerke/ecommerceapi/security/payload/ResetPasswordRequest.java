package com.akerke.ecommerceapi.security.payload;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank
        String password,
        @NotBlank
        String confirmPassword
) {
}

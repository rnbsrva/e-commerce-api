package com.akerke.ecommerceapi.security.payload;

public record AuthRequest(
        String email,
        String password
){}
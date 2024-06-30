package com.akerke.ecommerceapi.security.payload;

public record RegisterRequest(
        String email,
        String phone,
        String password,
        String firstName,
        String lastName
){
}

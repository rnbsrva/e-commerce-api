package com.akerke.ecommerceapi.security;

import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import com.akerke.ecommerceapi.security.payload.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(RegisterRequest registerRequest);
    void login(AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response);
    void confirmEmail(String token, HttpServletRequest request, HttpServletResponse response);
    void forgotPassword(String email);
    void resetPassword(String token, ResetPasswordRequest resetPasswordDto);
}

package com.akerke.ecommerceapi.security;

import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(RegisterRequest registerRequest,  HttpServletRequest request, HttpServletResponse response);
    void login(AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response);
    void confirmEmail(String token, HttpServletRequest request, HttpServletResponse response);

//    void resetPassword(AuthRquest authRquest); //TODO implement
//    void forgotPassword(AuthRquest authRquest);

}

package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.security.AuthService;
import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    void register( @RequestBody RegisterRequest registerRequest,
                  HttpServletRequest request,
                  HttpServletResponse response) {
        authService.register(registerRequest, request, response);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    void login(HttpServletRequest request,
               HttpServletResponse response,
               @RequestBody AuthRequest authRequest) {
        authService.login(authRequest, request, response);
    }

}

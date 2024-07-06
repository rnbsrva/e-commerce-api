package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.security.AuthService;
import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import com.akerke.ecommerceapi.security.payload.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    void login(HttpServletRequest request,
               HttpServletResponse response,
               @RequestBody @Valid AuthRequest authRequest) {
        authService.login(authRequest, request, response);
    }

    @GetMapping("/confirm-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void login(HttpServletRequest request,
               HttpServletResponse response,
               @RequestParam @NotBlank String token) {
        authService.confirmEmail(token, request, response);
    }


    @PostMapping("/forgot-password/{email}")
    @ResponseStatus(HttpStatus.OK)
    void forgotPassword(@PathVariable @NotBlank String email) {
        authService.forgotPassword(email);
    }

    @PostMapping("/reset-password/{token}")
    @ResponseStatus(HttpStatus.OK)
    void resetPassword(@PathVariable @NotBlank String token,
                       @RequestBody @Valid ResetPasswordRequest resetPasswordDto) {
        authService.resetPassword(token, resetPasswordDto);
    }

}

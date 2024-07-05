package com.akerke.ecommerceapi.security;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.common.exception.AuthException;
import com.akerke.ecommerceapi.domain.mapper.UserMapper;
import com.akerke.ecommerceapi.domain.model.ConfirmationToken;
import com.akerke.ecommerceapi.domain.model.User;
import com.akerke.ecommerceapi.domain.repository.UserRepository;
import com.akerke.ecommerceapi.domain.service.ConfirmationTokenService;
import com.akerke.ecommerceapi.domain.service.EmailService;
import com.akerke.ecommerceapi.domain.service.RoleService;
import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    private final static String EMAIL_CONFIRMATION_LINK_PATTERN = "http://localhost:8080/api/v1/auth/confirm-email?token=%s";

    @Override
    public void register(RegisterRequest registerRequest, HttpServletRequest request, HttpServletResponse response) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new AuthException("Email already exists");
        }

        if (userRepository.existsByPhone(registerRequest.phone())) {
            throw new AuthException("Phone already exists");
        }

        var user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRoles(Collections.singleton(roleService.findByType(RoleType.USER)));
        userRepository.save(user);

        sendVerificationToken(user);

    }

    private void sendVerificationToken(User user) {
        var token = confirmationTokenService.generateAndSaveToken(user, ConfirmationTokenType.EMAIL_CONFIRMATION);
        String verificationUrl = EMAIL_CONFIRMATION_LINK_PATTERN.formatted(token);

        emailService.sendConfirmationEmail(user.getEmail(), verificationUrl, user.getName());

    }

    @Override
    public void login(AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                authRequest.email(), authRequest.password());
        authenticate(token, request, response);
    }

    @Override
    public void confirmEmail(String token, HttpServletRequest request, HttpServletResponse response) {
        var confirmationToken = confirmationTokenService.findByToken(token);

        var isTokenExpired = confirmationToken.getExpirationDate().isBefore(LocalDateTime.now());

        if (isTokenExpired) {
            throw new AuthException("Token expired");
        }

        confirmationTokenService.deleteByToken(confirmationToken);

    }

    private void authenticate(UsernamePasswordAuthenticationToken token, HttpServletRequest request, HttpServletResponse response) {
        var authentication = authenticationManager.authenticate(token);
        var context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
    }

}

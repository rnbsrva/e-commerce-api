package com.akerke.ecommerceapi.security;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.common.exception.AuthException;
import com.akerke.ecommerceapi.core.mapper.UserMapper;
import com.akerke.ecommerceapi.model.ConfirmationToken;
import com.akerke.ecommerceapi.model.User;
import com.akerke.ecommerceapi.service.ConfirmationTokenService;
import com.akerke.ecommerceapi.service.EmailService;
import com.akerke.ecommerceapi.service.RoleService;
import com.akerke.ecommerceapi.service.UserService;
import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import com.akerke.ecommerceapi.security.payload.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    private final static String EMAIL_CONFIRMATION_LINK_PATTERN = "http://localhost:8080/api/v1/auth/confirm-email?token=%s";
    private final static String RESET_PASSWORD_LINK_PATTERN = "http://localhost:8080/api/v1/auth/reset-password/%s";

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.email())) {
            throw new AuthException("Email already exists");
        }

        if (userService.existsByPhone(registerRequest.phone())) {
            throw new AuthException("Phone already exists");
        }

        var user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRoles(Collections.singleton(roleService.findByType(RoleType.USER)));
        userService.save(user);

        sendVerificationToken(user);

    }

    private void sendVerificationToken(User user) {
        var token = confirmationTokenService.generateAndSaveToken(user, ConfirmationTokenType.EMAIL_CONFIRMATION);
        String confirmationLink = EMAIL_CONFIRMATION_LINK_PATTERN.formatted(token);

        emailService.sendEmail(
                user.getEmail(),
                "Email Confirmation",
                "confirmation-mail.ftl",
                Map.of("name", user.getName(), "confirmationLink", confirmationLink));
    }

    @Override
    public void login(AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
        var token = UsernamePasswordAuthenticationToken.unauthenticated(
                authRequest.email(), authRequest.password());
        authenticate(token, request, response);
    }

    @Override
    public void confirmEmail(String token, HttpServletRequest request, HttpServletResponse response) {
        var confirmationToken = validateToken(token, ConfirmationTokenType.EMAIL_CONFIRMATION);

        var user = confirmationToken.getUser();
        user.setConfirmed(true);
        userService.save(user);

        confirmationTokenService.deleteByToken(confirmationToken);
    }

    @Override
    public void forgotPassword(String email) {
        var user = userService.findByEmail(email);

        var token = confirmationTokenService.generateAndSaveToken(user, ConfirmationTokenType.PASSWORD_RESET);

        String resetPasswordLink = RESET_PASSWORD_LINK_PATTERN.formatted(token);

        emailService.sendEmail(
                user.getEmail(),
                "Reset Password",
                "reset-password.ftl",
                Map.of("name", user.getName(), "resetPasswordLink", resetPasswordLink));
    }

    @Override
    public void resetPassword(String token, ResetPasswordRequest resetPasswordDto) {
        if  (!resetPasswordDto.confirmPassword().equals(resetPasswordDto.password())) {
            throw new AuthException("Passwords do not match");
        }

        var confirmationToken = validateToken(token, ConfirmationTokenType.PASSWORD_RESET);

        var user = confirmationToken.getUser();
        user.setPassword(passwordEncoder.encode(resetPasswordDto.password()));
        userService.save(user);

        confirmationTokenService.deleteByToken(confirmationToken);
    }

    private void authenticate(UsernamePasswordAuthenticationToken token, HttpServletRequest request, HttpServletResponse response) {
        var authentication = authenticationManager.authenticate(token);
        var context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
    }

    private ConfirmationToken validateToken(String token, ConfirmationTokenType tokenType) {
        var confirmationToken = confirmationTokenService.findByToken(token);

        var isTokenExpired = confirmationToken.getExpirationDate().isBefore(LocalDateTime.now());
        if (isTokenExpired) {
            throw new AuthException("Token expired");
        }

        if (!confirmationToken.getConfirmationTokenType().equals(tokenType)) {
            throw new AuthException("Invalid token type");
        }

        return confirmationToken;
    }


}

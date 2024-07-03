package com.akerke.ecommerceapi.security;

import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.common.exception.AuthException;
import com.akerke.ecommerceapi.domain.mapper.UserMapper;
import com.akerke.ecommerceapi.domain.repository.UserRepository;
import com.akerke.ecommerceapi.domain.service.RoleService;
import com.akerke.ecommerceapi.security.payload.AuthRequest;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
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

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;

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

        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                registerRequest.email(), registerRequest.password());
        authenticate(token, request, response);

    }

    @Override
    public void login(AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                authRequest.email(), authRequest.password());
        authenticate(token, request, response);
    }

    private void authenticate(UsernamePasswordAuthenticationToken token, HttpServletRequest request, HttpServletResponse response) {
        var authentication = authenticationManager.authenticate(token);
        var context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
    }

}

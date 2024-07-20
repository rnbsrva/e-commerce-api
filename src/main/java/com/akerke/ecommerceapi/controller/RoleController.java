package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.enums.SafetyRole;
import com.akerke.ecommerceapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

    private final AuthService authService;

    @PreAuthorize("hasRole('APPLICATION_ADMIN')")
    @PostMapping("/assign")
    void assignModeratorRole(
            @RequestParam Long userId) {
        authService.assignRole(userId, SafetyRole.MODERATOR);
    }

    @PreAuthorize("hasRole('APPLICATION_ADMIN')")
    @PostMapping("/remove")
    void removeModeratorRole(
            @RequestParam Long userId
    ) {
        authService.removeRole(userId, SafetyRole.MODERATOR);
    }

}

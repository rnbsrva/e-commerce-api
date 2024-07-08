package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.core.event.SellerRoleAssignedEvent;
import com.akerke.ecommerceapi.security.AuthService;
import com.akerke.ecommerceapi.security.EcommerceUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

    private final AuthService authService;
    private final ApplicationEventPublisher eventPublisher;

    @PreAuthorize("@roleController.hasRequiredRole(#roleType)")
    @PostMapping("/assign")
    void assignRole(
            @RequestParam Long userId,
            @RequestParam RoleType roleType) {
        authService.assignRole(userId, roleType);
        if (roleType == RoleType.SELLER) {
            eventPublisher.publishEvent(new SellerRoleAssignedEvent(this, userId));
        }
    }

    @PreAuthorize("@roleController.hasRequiredRole(#roleType)")
    @PostMapping("/remove")
    void removeRole(
            @RequestParam Long userId,
            @RequestParam RoleType roleType
    ) {
        authService.removeRole(userId, roleType);
    }

    private boolean hasRequiredRole(RoleType roleType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        var roles = ((EcommerceUserDetails) authentication.getPrincipal()).getRoleTypes();

        return switch (roleType) {
            case MODERATOR -> roles.contains(RoleType.APPLICATION_ADMIN);
            case SELLER -> (roles.contains(RoleType.APPLICATION_ADMIN) || roles.contains(RoleType.MANAGER));
            case MANAGER -> roles.contains(RoleType.SELLER);
            default -> false;
        };
    }

}

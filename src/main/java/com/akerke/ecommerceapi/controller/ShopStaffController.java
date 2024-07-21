package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.service.ShopStaffService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@Slf4j
public class ShopStaffController {

    private final ShopStaffService shopStaffService;

    @PostMapping
    public void saveManager(
            @RequestParam @NotNull Long shopId,
            @RequestParam @NotNull Long userId,
            Authentication authentication) {
        shopStaffService.saveManager(shopId, userId, authentication);
    }

}

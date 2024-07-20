package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.dto.CreateShopRequest;
import com.akerke.ecommerceapi.common.dto.ShopRequestDto;
import com.akerke.ecommerceapi.service.ShopRequestService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/shop-request")
@RequiredArgsConstructor
public class ShopRequestController {

    private final ShopRequestService shopRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createShopRequest(
            @RequestBody CreateShopRequest createShopRequest,
            Authentication authentication
    ) {
        shopRequestService.save(createShopRequest, authentication);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('APPLICATION_ADMIN', 'MODERATOR')")
    public void handlePendingShopRequest(
            @PathVariable @NotNull Long id,
            @RequestParam @NotNull Boolean isApproved,
            @RequestParam @Nullable String reason,
            Authentication authentication
    ) {
        shopRequestService.handlePendingShopRequest(id, isApproved, reason, authentication);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('APPLICATION_ADMIN', 'MODERATOR')")
    public ResponseEntity<List<ShopRequestDto>> getAll(
            Pageable pageable
    ) {
        return ResponseEntity.ok(shopRequestService.getAll(pageable));
    }

}

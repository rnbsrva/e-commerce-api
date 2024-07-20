package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.dto.CreateShopRequest;
import com.akerke.ecommerceapi.common.dto.ShopRequestDto;
import com.akerke.ecommerceapi.common.enums.RequestStatus;
import com.akerke.ecommerceapi.security.EcommerceUserDetails;
import com.akerke.ecommerceapi.service.ShopRequestService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shop-request")
@RequiredArgsConstructor
@Slf4j
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
    @PreAuthorize("hasAnyAuthority('APPLICATION_ADMIN', 'MODERATOR')")
    public void handlePendingShopRequest(
            @PathVariable @NotNull Long id,
            @RequestParam @NotNull RequestStatus requestStatus,
            @RequestParam @Nullable String reason,
            Authentication authentication
    ) {
        shopRequestService.handlePendingShopRequest(id, requestStatus, reason, authentication);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('APPLICATION_ADMIN', 'MODERATOR')")
    public ResponseEntity<List<ShopRequestDto>> getAll(
            Pageable pageable
    ) {
        return ResponseEntity.ok(shopRequestService.getAll(pageable));
    }

}

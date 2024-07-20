package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.dto.CreateShopRequest;
import com.akerke.ecommerceapi.common.dto.ShopRequestDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ShopRequestService {

    void save(CreateShopRequest createShopRequest, Authentication authentication);

    List<ShopRequestDto> getAll(Pageable pageable);

    void handlePendingShopRequest(Long id, Boolean isApproved, @Nullable String reason, Authentication authentication);

}

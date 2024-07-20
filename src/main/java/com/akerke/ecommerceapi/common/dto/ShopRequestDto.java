package com.akerke.ecommerceapi.common.dto;

import com.akerke.ecommerceapi.common.enums.RequestStatus;
import com.akerke.ecommerceapi.common.enums.ShopType;
import com.akerke.ecommerceapi.model.User;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record ShopRequestDto(
        Long id,
        String name,
        String description,
        ShopType shopType,
        String address,
        String phone,
        RequestStatus requestStatus,
        String website,
        String rejectedReason,
        LocalDateTime reviewedAt
        ) {

}

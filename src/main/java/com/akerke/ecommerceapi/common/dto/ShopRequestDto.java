package com.akerke.ecommerceapi.common.dto;

import com.akerke.ecommerceapi.common.enums.RequestStatus;
import com.akerke.ecommerceapi.common.enums.ShopType;
import com.akerke.ecommerceapi.model.User;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record ShopRequestDto(
        String name,
        String description,
        ShopType shopType,
        String address,
        String phone,
        RequestStatus status,
        String website,
        Boolean isApproved,
        String rejectedReason,
        LocalDateTime reviewedAt
        ) {

}

package com.akerke.ecommerceapi.common.dto;

import com.akerke.ecommerceapi.common.enums.ShopType;
import com.akerke.ecommerceapi.core.annotation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateShopRequest(
        @NotBlank @Size(min = 3, max = 50)
        String name,
        @NotBlank @Size(min = 8, max = 255)
        String description,
        @NotBlank
        ShopType shopType,
        String address,
        @PhoneNumber
        String phone,
        String website
) {
}

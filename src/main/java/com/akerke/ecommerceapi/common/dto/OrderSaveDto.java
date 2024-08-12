package com.akerke.ecommerceapi.common.dto;

public record OrderSaveDto(
        Long shopProductId,
        Integer quantity
) {
}

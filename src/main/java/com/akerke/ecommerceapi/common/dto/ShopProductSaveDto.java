package com.akerke.ecommerceapi.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ShopProductSaveDto(
        @NotNull @Positive
        Double price,
        @NotNull @Positive
        Integer quantity
) {
}

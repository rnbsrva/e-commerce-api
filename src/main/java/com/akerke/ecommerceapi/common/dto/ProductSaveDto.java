package com.akerke.ecommerceapi.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductSaveDto(
        @NotBlank @Size(min = 3, max = 50)
        String name,
        @NotBlank @Size(min = 3, max = 255)
        String description,
        @NotNull @Positive
        Double price,
        @NotNull
        Long categoryId,
        @NotNull
        ShopProductSaveDto shopProductSaveDto
) {
}

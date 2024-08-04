package com.akerke.ecommerceapi.common.dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductSaveDto(
        @NotBlank @Size(min = 3, max = 50)
        String name,
        @NotBlank @Size(min = 3, max = 255)
        String description,
        @NotNull @Positive
        Double price,
        @NotNull
        Long shopId,
        @NotNull
        Long categoryId,
        @NotEmpty  @Size(min = 1, max = 5)
        List<MultipartFile> images,
        @NotNull @PositiveOrZero
        Integer quantity
) {
}

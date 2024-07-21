package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    void save (
            @RequestBody ProductSaveDto productSaveDto,
            @RequestParam @NotNull Long shopId
            ) {
        productService.save(productSaveDto, shopId);
    }

}

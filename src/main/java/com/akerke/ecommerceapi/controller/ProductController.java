package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.elasticsearch.ProductSearch;
import com.akerke.ecommerceapi.elasticsearch.ProductSearchService;
import com.akerke.ecommerceapi.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductSearchService productSearchService;

    @PostMapping()

    @ResponseStatus(HttpStatus.ACCEPTED)
    void save (
            @RequestBody ProductSaveDto productSaveDto,
            @RequestParam @NotNull Long shopId
            ) {
        productService.save(productSaveDto, shopId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ProductSearch>> search(@RequestParam String query) {
        return ResponseEntity.ok(productSearchService.search(query));
    }

}

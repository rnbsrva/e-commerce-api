package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.common.dto.ShopProductSaveDto;
import com.akerke.ecommerceapi.model.Product;
import com.akerke.ecommerceapi.model.ShopProduct;
import org.springframework.security.core.Authentication;

public interface ShopProductService {

    void addShopProduct(ProductSaveDto productSaveDto, Long productId);

    ShopProduct getById(Long shopProductId);
}

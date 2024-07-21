package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.dto.ShopProductSaveDto;
import org.springframework.security.core.Authentication;

public interface ShopProductService {

    void addShopProduct(ShopProductSaveDto shopProductSaveDto, Long productId, Long shopId);

}

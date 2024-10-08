package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.common.dto.ShopProductSaveDto;
import com.akerke.ecommerceapi.core.annotation.CheckStaff;
import com.akerke.ecommerceapi.core.mapper.ShopProductMapper;
import com.akerke.ecommerceapi.model.Product;
import com.akerke.ecommerceapi.model.ShopProduct;
import com.akerke.ecommerceapi.repository.ShopProductRepository;
import com.akerke.ecommerceapi.security.EcommerceUserDetails;
import com.akerke.ecommerceapi.service.ProductService;
import com.akerke.ecommerceapi.service.ShopProductService;
import com.akerke.ecommerceapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopProductServiceImpl implements ShopProductService {

    private final ShopProductRepository shopProductRepository;
    private final ShopProductMapper shopProductMapper;
    @Lazy
    private final ProductService productService;
    private final ShopService shopService;

    @Override
    @CheckStaff
    public void addShopProduct(ProductSaveDto productSaveDto, Long productId){
        var shop = shopService.findById(productSaveDto.shopId());
        var product = productService.findById(productId);

        var shopProduct = shopProductMapper.toShopProduct(productSaveDto);
        shopProduct.setShop(shop);
        shopProduct.setProduct(product);
        shopProductRepository.save(shopProduct);
    }

    @Override
    public ShopProduct getById(Long shopProductId) {
        return shopProductRepository.findByID(shopProductId);
    }
}

package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.core.mapper.ProductMapper;
import com.akerke.ecommerceapi.model.Product;
import com.akerke.ecommerceapi.repository.ProductRepository;
import com.akerke.ecommerceapi.service.CategoryService;
import com.akerke.ecommerceapi.service.ProductService;
import com.akerke.ecommerceapi.service.ShopProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final ShopProductService shopProductService;

    @Override
    public Product save(ProductSaveDto productSaveDto, Long shopId) {
        var product = productMapper.toProduct(productSaveDto);
        product.setCategory(categoryService.findById(productSaveDto.categoryId()));

        var savedProduct =  productRepository.save(product);

        shopProductService.addShopProduct(productSaveDto.shopProductSaveDto(), savedProduct.getId(), shopId);

        return savedProduct;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findByID(id);
    }

}

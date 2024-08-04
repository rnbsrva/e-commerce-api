package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.model.Product;
import org.springframework.security.core.Authentication;

public interface ProductService {

    Product save(ProductSaveDto productSaveDto);

    Product findById(Long id);
//
//    void deleteById(Long id);
//
//    Product update(Product product);

}

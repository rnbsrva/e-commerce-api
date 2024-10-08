package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ShopProduct;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepository extends CommonRepository<ShopProduct, Long> {

    default Class<?> entityClass() {
        return ShopProduct.class;
    }

}

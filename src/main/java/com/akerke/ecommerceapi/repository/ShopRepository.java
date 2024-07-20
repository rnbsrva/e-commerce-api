package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends CommonRepository<Shop, Long> {

    default Class<?> entityClass() {
        return Shop.class;
    }

}

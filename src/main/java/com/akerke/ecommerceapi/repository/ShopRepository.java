package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends CommonRepository<Shop, Long> {

    default Class<?> entityClass() {
        return Shop.class;
    }

}

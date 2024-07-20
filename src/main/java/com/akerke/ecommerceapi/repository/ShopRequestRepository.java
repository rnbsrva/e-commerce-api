package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ShopRequest;

public interface ShopRequestRepository extends CommonRepository<ShopRequest, Long> {

    default Class<?> entityClass() {
        return ShopRequest.class;
    }

}

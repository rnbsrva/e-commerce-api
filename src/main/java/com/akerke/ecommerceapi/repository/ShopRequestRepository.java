package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ShopRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRequestRepository extends CommonRepository<ShopRequest, Long> {

    default Class<?> entityClass() {
        return ShopRequest.class;
    }

}

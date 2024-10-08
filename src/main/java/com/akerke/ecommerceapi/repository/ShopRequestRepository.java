package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ShopRequest;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRequestRepository extends CommonRepository<ShopRequest, Long> {

    default Class<?> entityClass() {
        return ShopRequest.class;
    }

}

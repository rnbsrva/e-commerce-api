package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.OrderStatusChange;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusChangeRepository extends CommonRepository<OrderStatusChange, Long> {

    @Override
    default Class<?> entityClass() {
        return OrderStatusChange.class;
    }

}

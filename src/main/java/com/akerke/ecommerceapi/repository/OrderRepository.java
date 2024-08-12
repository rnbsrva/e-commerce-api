package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Order;
import com.akerke.ecommerceapi.model.User;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CommonRepository<Order, Long> {

    @Override
    default Class<?> entityClass() {
        return Order.class;
    }

    List<Order> findAllByUser(User user);
}

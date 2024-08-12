package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.dto.OrderSaveDto;
import com.akerke.ecommerceapi.common.dto.OrderStatusDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    OrderStatusDto createOrder(OrderSaveDto order, Authentication authentication);

    List<OrderStatusDto> getUnacceptedOrdersByUser(Authentication authentication);

}

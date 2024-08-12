package com.akerke.ecommerceapi.common.dto;

import com.akerke.ecommerceapi.common.enums.OrderStatus;

public record OrderStatusDto (
        OrderStatus orderStatus,
        Long shopProductId,
        String productName,
        Integer quantity,
        Double total
){}

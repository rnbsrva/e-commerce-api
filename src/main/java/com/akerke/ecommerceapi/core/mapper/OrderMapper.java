package com.akerke.ecommerceapi.core.mapper;

import com.akerke.ecommerceapi.common.dto.OrderSaveDto;
import com.akerke.ecommerceapi.common.dto.OrderStatusDto;
import com.akerke.ecommerceapi.common.enums.OrderStatus;
import com.akerke.ecommerceapi.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(imports = {OrderStatus.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "status", expression = "java(OrderStatus.CREATED)")
    Order toModel(OrderSaveDto orderSaveDto);

    @Mapping(target = "productName", source = "order.shopProduct.product.name")
    @Mapping(target = "shopProductId", source = "order.shopProduct.id")
    OrderStatusDto toOrderStatusDto(Order order);

}

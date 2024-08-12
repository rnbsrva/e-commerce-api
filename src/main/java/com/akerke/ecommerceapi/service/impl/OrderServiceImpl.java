package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.dto.OrderSaveDto;
import com.akerke.ecommerceapi.common.dto.OrderStatusDto;
import com.akerke.ecommerceapi.core.mapper.OrderMapper;
import com.akerke.ecommerceapi.model.Order;
import com.akerke.ecommerceapi.model.ShopProduct;
import com.akerke.ecommerceapi.model.User;
import com.akerke.ecommerceapi.repository.OrderRepository;
import com.akerke.ecommerceapi.service.OrderService;
import com.akerke.ecommerceapi.service.ShopProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShopProductService shopProductService;

    @Override
    public OrderStatusDto createOrder(OrderSaveDto orderSaveDto, Authentication authentication) {
        Order order = orderMapper.toModel(orderSaveDto);
        ShopProduct shopProduct = shopProductService.getById(orderSaveDto.shopProductId());

        order.setShopProduct(shopProduct);
        order.setPrice(shopProduct.getPrice());
        order.setUser((User) authentication.getPrincipal());
        order.setTotal(orderSaveDto.quantity()*shopProduct.getPrice());

        var savedOrder = orderRepository.save(order);

        return orderMapper.toOrderStatusDto(savedOrder);
    }

    @Override
    public List<OrderStatusDto> getUnacceptedOrdersByUser(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var orders = orderRepository.findAllByUser(user);

        return orders.stream().map(orderMapper::toOrderStatusDto).toList();
    }

}

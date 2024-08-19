package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.dto.OrderSaveDto;
import com.akerke.ecommerceapi.common.dto.OrderStatusDto;
import com.akerke.ecommerceapi.common.enums.OrderStatus;
import com.akerke.ecommerceapi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    ResponseEntity<OrderStatusDto> createOrder(
            @RequestBody @Valid OrderSaveDto orderSaveDto,
            Authentication authentication
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderSaveDto, authentication));
    }

    @GetMapping()
    ResponseEntity<List<OrderStatusDto>> getOrdersByUserAndStatus(
            Authentication authentication,
            OrderStatus orderStatus
    ) {
        return ResponseEntity.ok(orderService.getOrdersByUserAndStatus(authentication, orderStatus));
    }

}

package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.common.enums.OrderStatus;
import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private ShopProduct shopProduct;

    @ManyToOne
    private User user;

    private Integer quantity;

    private Double price;

    private Double total;


}

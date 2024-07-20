package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.common.enums.OrderStatus;
import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "order_status_changes")
public class OrderStatusChange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private ShopStaff changedBy;

    @Enumerated(EnumType.STRING)
    private OrderStatus newStatus;

//    changedAt date is not needed because it is already in BaseEntity

}
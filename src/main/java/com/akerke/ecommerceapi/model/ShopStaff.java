package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ShopStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private User user;

    @Enumerated(EnumType.STRING)
    private ShopRole shopRole;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Shop shop;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "changedBy"
    )
    private List<OrderStatusChange> orderStatusChanges;
}

package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ShopProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Boolean available;

    private Double price;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Product product;

    @OneToMany(
            mappedBy = "shopProduct",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Order> orders;
}

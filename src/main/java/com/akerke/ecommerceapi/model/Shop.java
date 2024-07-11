package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String address;

    private String phone;

    private String mainImage;

    @ManyToOne
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shop_manager",
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id", referencedColumnName = "id")
    )
    private List<User> managers;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "shop"
    )
    private List<ShopProduct> shopProducts;

}

package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.elasticsearch.ProductEntityListener;
import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(ProductEntityListener.class)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<ShopProduct> shopProducts;

    @ManyToOne
    private Category category;

}

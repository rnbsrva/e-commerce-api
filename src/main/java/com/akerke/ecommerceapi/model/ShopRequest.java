package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.common.enums.ShopType;
import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ShopRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private ShopType shopType;
    private String address;
    private String phone;
    private String website;
    @ManyToOne
    private User user;

    private Boolean isApproved;
    private String rejectedReason;
    @ManyToOne
    private User reviewedBy;
    private LocalDateTime reviewedAt;
}

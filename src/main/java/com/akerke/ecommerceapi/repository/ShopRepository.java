package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}

package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.model.ShopStaff;
import org.springframework.security.core.Authentication;

public interface ShopStaffService {

    ShopStaff saveSeller(Long shopId, Long userId);

    ShopStaff saveManager(Long shopId, Long userId);

}

package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.model.ShopStaff;

public interface ShopStaffService {

    ShopStaff save(Long shopId, Long userId, ShopRole role);

}

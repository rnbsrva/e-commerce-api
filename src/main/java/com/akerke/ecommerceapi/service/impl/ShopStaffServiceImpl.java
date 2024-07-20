package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.model.ShopStaff;
import com.akerke.ecommerceapi.repository.ShopStaffRepository;
import com.akerke.ecommerceapi.service.ShopService;
import com.akerke.ecommerceapi.service.ShopStaffService;
import com.akerke.ecommerceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopStaffServiceImpl implements ShopStaffService {

    private final ShopStaffRepository shopStaffRepository;
    @Lazy
    private final ShopService shopService;
    private final UserService userService;

    @Override
    public ShopStaff save(Long shopId, Long userId, ShopRole role) {
        var shopStaff = new ShopStaff();

        shopStaff.setShop(shopService.findById(shopId));
        shopStaff.setUser(userService.findById(userId));
        shopStaff.setShopRole(role);
        shopStaff.setOrderStatusChanges(new ArrayList<>());

        return shopStaffRepository.save(shopStaff);
    }

}

package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.core.annotation.CheckStaff;
import com.akerke.ecommerceapi.core.event.ManagerRoleAssignedEvent;
import com.akerke.ecommerceapi.core.mapper.ShopStaffMapper;
import com.akerke.ecommerceapi.model.ShopStaff;
import com.akerke.ecommerceapi.repository.ShopStaffRepository;
import com.akerke.ecommerceapi.service.ShopService;
import com.akerke.ecommerceapi.service.ShopStaffService;
import com.akerke.ecommerceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopStaffServiceImpl implements ShopStaffService {

    private final ShopStaffRepository shopStaffRepository;
    @Lazy
    private final ShopService shopService;
    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;
    private final ShopStaffMapper shopStaffMapper;

    @Override
    public ShopStaff saveSeller(Long shopId, Long userId) {
        var shop = shopService.findById(shopId);
        var user = userService.findById(userId);

        var shopStaff = shopStaffMapper.toShopStaff(shop, user, ShopRole.SELLER);
        return shopStaffRepository.save(shopStaff);
    }

    @Override
    @CheckStaff
    public ShopStaff saveManager(Long shopId, Long userId) {
        var shop = shopService.findById(shopId);
        var manager = userService.findById(userId);

        if (shop.getShopStaffs().stream().anyMatch(staff -> staff.getUser().getId().equals(manager.getId()))) {
            throw new IllegalArgumentException("User is already a staff of this shop");
        }

        var shopStaff = shopStaffMapper.toShopStaff(shop, manager, ShopRole.MANAGER);

        eventPublisher.publishEvent(new ManagerRoleAssignedEvent(this, userId));

        return shopStaffRepository.save(shopStaff);
    }

}

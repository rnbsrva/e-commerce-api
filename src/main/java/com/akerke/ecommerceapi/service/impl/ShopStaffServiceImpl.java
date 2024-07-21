package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.core.event.ManagerRoleAssignedEvent;
import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopStaff;
import com.akerke.ecommerceapi.model.User;
import com.akerke.ecommerceapi.repository.ShopStaffRepository;
import com.akerke.ecommerceapi.security.EcommerceUserDetails;
import com.akerke.ecommerceapi.service.ShopService;
import com.akerke.ecommerceapi.service.ShopStaffService;
import com.akerke.ecommerceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopStaffServiceImpl implements ShopStaffService {

    private final ShopStaffRepository shopStaffRepository;
    @Lazy
    private final ShopService shopService;
    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;

    @Override
    public ShopStaff saveSeller(Long shopId, Long userId) {
        var shopStaff = new ShopStaff();

        shopStaff.setShop(shopService.findById(shopId));
        shopStaff.setUser(userService.findById(userId));
        shopStaff.setShopRole(ShopRole.SELLER);
        shopStaff.setOrderStatusChanges(new ArrayList<>());

        return shopStaffRepository.save(shopStaff);
    }

    public ShopStaff saveManager(Long shopId, Long userId, Authentication authentication) {
        var shop = shopService.findById(shopId);
        var manager = userService.findById(userId);
        var possibleOwner = ((EcommerceUserDetails) authentication.getPrincipal()).user();

        if (shop.getShopStaffs().stream().anyMatch(staff -> staff.getUser().getId().equals(manager.getId()))) {
            throw new IllegalArgumentException("User is already a staff of this shop");
        }

        checkIfUserIsOwner(shop, possibleOwner);

        var shopStuff = new ShopStaff();
        shopStuff.setShop(shop);
        shopStuff.setUser(manager);
        shopStuff.setShopRole(ShopRole.MANAGER);
        shopStuff.setOrderStatusChanges(new ArrayList<>());

        eventPublisher.publishEvent(new ManagerRoleAssignedEvent(this, userId));

        return shopStaffRepository.save(shopStuff);
    }

    private void checkIfUserIsOwner(Shop shop,  User possibleOwner) {
        boolean isOwner = shop.getShopStaffs().stream()
                .filter(staff -> staff.getShopRole() == ShopRole.SELLER)
                .anyMatch(staff -> staff.getUser().getId().equals(possibleOwner.getId()));

        if (!isOwner) {
            throw new IllegalArgumentException("Only the owner can add a manager");
        }
    }


}

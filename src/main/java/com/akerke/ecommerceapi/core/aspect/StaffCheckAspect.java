package com.akerke.ecommerceapi.core.aspect;

import com.akerke.ecommerceapi.core.annotation.CheckStaff;
import com.akerke.ecommerceapi.security.EcommerceUserDetails;
import com.akerke.ecommerceapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class StaffCheckAspect {

    private final ShopService shopService;

    @Before("@annotation(checkStaff) && args(shopId, ..)")
    public void checkStaffPermission(CheckStaff checkStaff, Long shopId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = ((EcommerceUserDetails) authentication.getPrincipal()).user();
        var shop = shopService.findById(shopId);

        if (shop.getShopStaffs().stream().noneMatch(shopStaff -> shopStaff.getUser().equals(user))) {
            throw new IllegalArgumentException("You are not a staff of this shop");
        }

    }

}
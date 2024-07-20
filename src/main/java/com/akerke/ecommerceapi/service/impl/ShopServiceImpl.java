package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.core.mapper.ShopMapper;
import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopRequest;
import com.akerke.ecommerceapi.repository.ShopRepository;
import com.akerke.ecommerceapi.service.ShopService;
import com.akerke.ecommerceapi.service.ShopStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;
    @Lazy
    private final ShopStaffService shopStaffService;

    @Override
    public Shop save(ShopRequest shopRequest) {
        var shop = shopMapper.toShop(shopRequest);
        var savedShop = shopRepository.save(shop);
        var shopOwner = shopStaffService.save(savedShop.getId(), shopRequest.getUser().getId(), ShopRole.SELLER);
        savedShop.getShopStaffs().add(shopOwner);
        return shopRepository.save(savedShop);
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findByID(id);
    }

}

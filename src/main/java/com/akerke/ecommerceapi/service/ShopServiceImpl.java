package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.core.mapper.ShopMapper;
import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopRequest;
import com.akerke.ecommerceapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    @Override
    public Shop save(ShopRequest shopRequest) {
        var shop = shopMapper.toShop(shopRequest);
        var savedShop = shopRepository.save(shop);
//        var shopOwner = shopStaffService(savedShop.getId(), shopRequest.getUser(), ShopRole.SELLER);
//        savedShop.getShopStaffs().add(shopStaff); // add shop owner to shop staffs TODO
        return savedShop;
    }

}

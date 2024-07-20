package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopRequest;

public interface ShopService {

    Shop save(ShopRequest shopRequest);

    Shop findById(Long id);

}

package com.akerke.ecommerceapi.core.mapper;

import com.akerke.ecommerceapi.common.enums.ShopRole;
import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopStaff;
import com.akerke.ecommerceapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;

@Mapper(imports = { ArrayList.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShopStaffMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shop", source = "shop")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "shopRole", source = "shopRole")
    @Mapping(target = "orderStatusChanges", expression = "java(new ArrayList<>())")
    ShopStaff toShopStaff(Shop shop, User user, ShopRole shopRole);

}

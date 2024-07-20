package com.akerke.ecommerceapi.core.mapper;

import com.akerke.ecommerceapi.common.dto.CreateShopRequest;
import com.akerke.ecommerceapi.common.dto.ShopRequestDto;
import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShopMapper {

    @Mapping(target = "id", ignore = true)
    ShopRequest toShopRequest(CreateShopRequest createShopRequest);

    @Mapping(target = "id", ignore = true)
    Shop toShop(ShopRequest shopRequest);

    ShopRequestDto toShopRequestDto(ShopRequest shopRequest);

}

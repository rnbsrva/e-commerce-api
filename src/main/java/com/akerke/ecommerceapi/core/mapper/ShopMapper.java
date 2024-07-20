package com.akerke.ecommerceapi.core.mapper;

import com.akerke.ecommerceapi.common.dto.CreateShopRequest;
import com.akerke.ecommerceapi.common.dto.ShopRequestDto;
import com.akerke.ecommerceapi.common.enums.RequestStatus;
import com.akerke.ecommerceapi.model.Shop;
import com.akerke.ecommerceapi.model.ShopRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;

@Mapper(imports = {RequestStatus.class, ArrayList.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShopMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requestStatus", expression = "java(RequestStatus.PENDING)")
    ShopRequest toShopRequest(CreateShopRequest createShopRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shopStaffs", expression = "java(new ArrayList<>())")
    @Mapping(target = "shopProducts", expression = "java(new ArrayList<>())")
    Shop toShop(ShopRequest shopRequest);

    ShopRequestDto toShopRequestDto(ShopRequest shopRequest);

}

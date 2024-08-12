package com.akerke.ecommerceapi.core.mapper;

import com.akerke.ecommerceapi.common.dto.ProductSaveDto;
import com.akerke.ecommerceapi.common.dto.ShopProductSaveDto;
import com.akerke.ecommerceapi.model.ShopProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;

@Mapper(imports = {ArrayList.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShopProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "available", expression = "java(true)")
    @Mapping(target = "orders", expression = "java(new ArrayList<>())")
    ShopProduct toShopProduct(ProductSaveDto productSaveDto);


}

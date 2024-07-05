package com.akerke.ecommerceapi.domain.mapper;

import com.akerke.ecommerceapi.domain.model.User;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "confirmed", expression = "java(false)")
    User toUser(RegisterRequest registerRequest);

}

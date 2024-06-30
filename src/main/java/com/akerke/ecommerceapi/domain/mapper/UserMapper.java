package com.akerke.ecommerceapi.domain.mapper;

import com.akerke.ecommerceapi.domain.model.User;
import com.akerke.ecommerceapi.security.payload.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUser(RegisterRequest registerRequest);



}

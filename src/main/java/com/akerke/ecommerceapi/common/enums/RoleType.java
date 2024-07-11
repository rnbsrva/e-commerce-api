package com.akerke.ecommerceapi.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {

    APPLICATION_ADMIN,
    MODERATOR,
    SELLER,
    MANAGER,
    USER,
    GUEST;

}

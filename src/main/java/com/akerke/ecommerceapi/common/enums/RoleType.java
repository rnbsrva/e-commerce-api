package com.akerke.ecommerceapi.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {

    APPLICATION_ADMIN(Integer.MAX_VALUE),
    MODERATOR(4),
    SELLER(3),
    MANAGER(2),
    USER(1),
    GUEST(Integer.MIN_VALUE);

    private final int priority;
}

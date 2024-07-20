package com.akerke.ecommerceapi.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SafetyRole {

    APPLICATION_ADMIN,
    MODERATOR,
    USER,
    GUEST

}

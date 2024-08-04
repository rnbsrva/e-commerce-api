package com.akerke.ecommerceapi.common.enums;

import lombok.Getter;

@Getter
public enum ImageType {

    PRODUCT ("product"),
    REVIEW ("review");

    private final String name;

    ImageType(String name) {
        this.name = name;
    }
}

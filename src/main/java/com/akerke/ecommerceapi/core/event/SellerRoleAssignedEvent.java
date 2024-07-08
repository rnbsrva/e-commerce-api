package com.akerke.ecommerceapi.core.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SellerRoleAssignedEvent extends ApplicationEvent {

    private final Long userId;

    public SellerRoleAssignedEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

}

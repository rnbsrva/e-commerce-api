package com.akerke.ecommerceapi.model;

import com.akerke.ecommerceapi.common.enums.SafetyRole;
import com.akerke.ecommerceapi.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SafetyRole safetyRole;

    @Override
    public String getAuthority() {
        return this.safetyRole.name();
    }
}

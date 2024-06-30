package com.akerke.ecommerceapi.domain.service;

import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.domain.model.Role;

public interface RoleService {

    Role findByType(RoleType roleType);

}

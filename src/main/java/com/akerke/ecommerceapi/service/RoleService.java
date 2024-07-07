package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.model.Role;

public interface RoleService {

    Role findByType(RoleType roleType);

}

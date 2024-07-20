package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.enums.SafetyRole;
import com.akerke.ecommerceapi.model.Role;

public interface RoleService {

    Role findByType(SafetyRole safetyRole);

}

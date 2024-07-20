package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.enums.SafetyRole;
import com.akerke.ecommerceapi.model.Role;
import com.akerke.ecommerceapi.repository.RoleRepository;
import com.akerke.ecommerceapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByType(SafetyRole safetyRole) {
        return roleRepository.findBySafetyRole(safetyRole)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setSafetyRole(safetyRole);
                    return roleRepository.save(newRole);
                });
    }
}

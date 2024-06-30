package com.akerke.ecommerceapi.domain.service.impl;

import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.domain.model.Role;
import com.akerke.ecommerceapi.domain.repository.RoleRepository;
import com.akerke.ecommerceapi.domain.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleType(roleType);
                    return roleRepository.save(newRole);
                });
    }
}

package com.akerke.ecommerceapi.domain.repository;

import com.akerke.ecommerceapi.common.enums.RoleType;
import com.akerke.ecommerceapi.domain.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CommonRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleType name);

    @Override
    default Class<?> entityClass() {
        return Role.class;
    }

}

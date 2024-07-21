package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.common.enums.SafetyRole;
import com.akerke.ecommerceapi.model.Role;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CommonRepository<Role, Long> {

    Optional<Role> findBySafetyRole(SafetyRole name);

    @Override
    default Class<?> entityClass() {
        return Role.class;
    }

}

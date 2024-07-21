package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.User;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User, Long> {

    @Override
    default Class<?> entityClass() {
        return User.class;
    }

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}

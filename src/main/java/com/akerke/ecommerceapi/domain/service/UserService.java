package com.akerke.ecommerceapi.domain.service;

import com.akerke.ecommerceapi.domain.model.User;

public interface UserService {

    User save (User user);

    User findById(Long id);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

}

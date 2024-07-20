package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.model.User;

public interface UserService {

    void save (User user);

    User findById(Long id);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

}

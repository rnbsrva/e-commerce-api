package com.akerke.ecommerceapi.domain.service.impl;

import com.akerke.ecommerceapi.common.exception.EntityNotFoundException;
import com.akerke.ecommerceapi.domain.model.User;
import com.akerke.ecommerceapi.domain.repository.UserRepository;
import com.akerke.ecommerceapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findByID(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException(User.class, "email", email));
    }
}

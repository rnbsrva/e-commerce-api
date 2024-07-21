package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.model.Category;
import com.akerke.ecommerceapi.repository.CategoryRepository;
import com.akerke.ecommerceapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findByID(id);
    }
}

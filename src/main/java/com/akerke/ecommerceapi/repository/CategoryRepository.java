package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Category;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CommonRepository<Category, Long> {

    default Class<?> entityClass() {
        return Category.class;
    }

}

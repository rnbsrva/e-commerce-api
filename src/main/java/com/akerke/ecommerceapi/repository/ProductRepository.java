package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CommonRepository<Product, Long>{

    default Class<?> entityClass() {
        return Product.class;
    }

}

package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.Review;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CommonRepository<Review, Long> {

    default Class<?> entityClass() {
        return Review.class;
    }

}

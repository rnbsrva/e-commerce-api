package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ShopStaff;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopStaffRepository extends CommonRepository<ShopStaff, Long> {

    default Class<?> entityClass() {
        return ShopStaff.class;
    }

}

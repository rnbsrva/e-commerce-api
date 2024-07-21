package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ShopStaff;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopStaffRepository extends CommonRepository<ShopStaff, Long> {

    default Class<?> entityClass() {
        return ShopStaff.class;
    }

}

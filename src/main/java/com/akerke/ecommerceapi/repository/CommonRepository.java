package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.common.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface  CommonRepository<E, PK extends Serializable> extends JpaRepository<E, PK> {

    default E findByID(
            PK id
    ) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException(entityClass(), id));
    }

    Class<?> entityClass();

}

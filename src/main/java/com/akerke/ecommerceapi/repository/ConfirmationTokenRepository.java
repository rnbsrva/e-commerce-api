package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ConfirmationToken;
import com.akerke.ecommerceapi.repository.common.CommonRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CommonRepository<ConfirmationToken, Long> {

    @Override
    default Class<?> entityClass() {
        return ConfirmationToken.class;
    }

    Optional<ConfirmationToken> findByToken(String token);

    @Modifying
    @Query("SELECT ct FROM ConfirmationToken ct WHERE ct.expirationDate <= ?1")
    void deleteAllByExpirationDateSince(LocalDateTime now);

}

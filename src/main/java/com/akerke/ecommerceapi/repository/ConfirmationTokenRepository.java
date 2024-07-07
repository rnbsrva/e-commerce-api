package com.akerke.ecommerceapi.repository;

import com.akerke.ecommerceapi.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Query("SELECT ct FROM ConfirmationToken ct WHERE ct.expirationDate <= ?1")
    void deleteAllByExpirationDateSince(LocalDateTime now);

}

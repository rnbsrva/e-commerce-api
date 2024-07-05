package com.akerke.ecommerceapi.domain.repository;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import com.akerke.ecommerceapi.domain.model.ConfirmationToken;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Query("SELECT ct FROM ConfirmationToken ct WHERE ct.expirationDate <= ?1")
    void deleteAllByExpirationDateSince(LocalDateTime now);

}

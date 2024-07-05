package com.akerke.ecommerceapi.domain.service.impl;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import com.akerke.ecommerceapi.domain.model.ConfirmationToken;
import com.akerke.ecommerceapi.domain.model.User;
import com.akerke.ecommerceapi.domain.repository.ConfirmationTokenRepository;
import com.akerke.ecommerceapi.domain.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));
    }

    @Override
    public void deleteByToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }

    @Override
    public String generateAndSaveToken(User user, ConfirmationTokenType confirmationTokenType) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setUser(user);
        confirmationToken.setConfirmationTokenType(confirmationTokenType);
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(confirmationTokenType.getExpirationMinutes());
        confirmationToken.setExpirationDate(expirationDate);

        return confirmationTokenRepository.save(confirmationToken).getToken();
    }

}

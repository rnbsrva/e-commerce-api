package com.akerke.ecommerceapi.domain.service;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import com.akerke.ecommerceapi.domain.model.ConfirmationToken;
import com.akerke.ecommerceapi.domain.model.User;

public interface ConfirmationTokenService {

    String generateAndSaveToken(User user, ConfirmationTokenType confirmationTokenType);

    ConfirmationToken findByToken(String token);
    void deleteByToken(ConfirmationToken token);

}

package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import com.akerke.ecommerceapi.model.ConfirmationToken;
import com.akerke.ecommerceapi.model.User;

public interface ConfirmationTokenService {

    String generateAndSaveToken(User user, ConfirmationTokenType confirmationTokenType);

    ConfirmationToken findByToken(String token);
    void deleteByToken(ConfirmationToken token);

}

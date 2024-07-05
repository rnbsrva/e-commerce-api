package com.akerke.ecommerceapi.domain.service;

public interface EmailService {
    void sendConfirmationEmail(String to, String confirmationLink, String name);
}
package com.akerke.ecommerceapi.service;

import java.util.Map;

public interface EmailService {

    void sendEmail(String to, String subject, String templateName, Map<String, Object> model);
}
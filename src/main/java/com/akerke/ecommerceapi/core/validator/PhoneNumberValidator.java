package com.akerke.ecommerceapi.core.validator;

import com.akerke.ecommerceapi.core.annotation.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }
}
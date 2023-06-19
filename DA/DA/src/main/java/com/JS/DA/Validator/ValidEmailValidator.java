package com.JS.DA.Validator;

import com.JS.DA.Login.Repository.IEmailRepository;
import com.JS.DA.Validator.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Autowired
    private IEmailRepository emailRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (emailRepository == null)
            return true;
        return emailRepository.findByEmail(email) == null;
    }
}


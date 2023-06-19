package com.JS.DA.Validator;

import com.JS.DA.Login.Repository.IUserRepository;
import com.JS.DA.Validator.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IUserRepository userRepository;


    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(userRepository == null)
            return true;
        return userRepository.findByUsername(username) == null;
    }
}
package com.JS.DA.Validator;

import com.JS.DA.Login.Model.User;
import com.JS.DA.Validator.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null)
            return true;
        return user.getId() !=null;
    }
}

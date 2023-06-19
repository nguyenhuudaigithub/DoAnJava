package com.JS.DA.Validator.annotation;

import com.JS.DA.Validator.ValidEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidEmailValidator.class)
public @interface ValidEmail {
    String message() default "Địa chỉ email đã đăng ký!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}


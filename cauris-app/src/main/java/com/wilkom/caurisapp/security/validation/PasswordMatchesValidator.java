package com.wilkom.caurisapp.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wilkom.caurisapp.security.model.entity.MyUser;



public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final MyUser user = (MyUser) obj;
        return user.getPassword().equals(user.getMatchingPassord());
    }

}

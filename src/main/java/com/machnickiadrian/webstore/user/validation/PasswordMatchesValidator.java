package com.machnickiadrian.webstore.user.validation;

import com.machnickiadrian.webstore.user.dto.UserRegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for checking, if user's password and confirmation password typed
 * during registration match each other.
 *
 * @author Adrian Machnicki
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        UserRegisterDto userDto = (UserRegisterDto) object;
        return userDto.getPassword().equals(userDto.getPasswordConfirm());
    }

}
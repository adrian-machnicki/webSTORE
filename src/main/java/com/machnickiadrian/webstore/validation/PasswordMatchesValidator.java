package com.machnickiadrian.webstore.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.machnickiadrian.webstore.dto.UserDto;

/**
 * Validator for checking, if user's password and confirmation password typed
 * during registration match each other.
 * 
 * @author Adrian Machnicki
 *
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {

		UserDto userDto = (UserDto) object;
		return userDto.getPassword().equals(userDto.getPasswordConfirm());
	}

}
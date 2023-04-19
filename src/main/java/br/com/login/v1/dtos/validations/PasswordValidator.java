package br.com.login.v1.dtos.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

	public boolean isValid(String pwd, ConstraintValidatorContext cxt) {

		if (pwd.length() < 8) {
			return false;
		}

		String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
		boolean upper = false;
		boolean lower = false;
		boolean specialCharacter = false;

		for (int i = 0; i < pwd.length(); i++) {

			char c = pwd.charAt(i);

			if (Character.isLowerCase(c)) {
				lower = true;
			}

			if (Character.isUpperCase(c)) {
				upper = true;
			}

			if (specialChars.contains(pwd.substring(i, i + 1))) {
				specialCharacter = true;
			}
		}

		if (upper && lower && specialCharacter) {
			return true;
		}

		return false;
	}
}

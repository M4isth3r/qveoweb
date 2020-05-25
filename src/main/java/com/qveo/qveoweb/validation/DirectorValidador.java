package com.qveo.qveoweb.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qveo.qveoweb.model.Director;

@Component
public class DirectorValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Director.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Director director = (Director)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "error");
		
		
	}

}
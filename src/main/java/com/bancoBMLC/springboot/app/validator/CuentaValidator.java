package com.bancoBMLC.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoBMLC.springboot.app.models.entity.Cuenta;

@Component
public class CuentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// clase asignable
		return Cuenta.class.isAssignableFrom(clazz);
	}
	
//validacion del nombre y numero de telefono en la clase Cuenta
	@Override
	public void validate(Object target, Errors errors) {
		Cuenta cuenta = (Cuenta)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.cuenta.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTelefono", "NotEmpty.cuenta.numeroTelefono");
		
		
		if(! cuenta.getNombre().matches("(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)")) {
			errors.rejectValue("nombre", "format.cuenta.nombre");
		}
		
		if(! cuenta.getNumeroTelefono().matches("^\\d{10}$")) {
			errors.rejectValue("numeroTelefono", "format.cuenta.numeroTelefono");
		}
	}

}

package com.bancoBMLC.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoBMLC.springboot.app.models.entity.Tarjeta;

@Component
public class TarjetaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Tarjeta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Tarjeta tarjeta = (Tarjeta)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.tarjeta.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTarjeta", "NotEmpty.tarjeta.numeroTarjeta");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "icv", "NotEmpty.tarjeta.icv");
		
		if(! tarjeta.getNombre().matches("(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)")) {
			errors.rejectValue("nombre", "NotEmpty.tarjeta.nombre");
		}
		
		if(! tarjeta.getNumeroTarjeta().matches("^\\d{18}$")) {
			errors.rejectValue("numeroTarjeta", "NotEmpty.tarjeta.numeroTarjeta");
		}
		
		if(! tarjeta.getIcv().matches("^\\d{3}$")) {
			errors.rejectValue("icv", "NotEmpty.tarjeta.icv");
		}
	}
	
}

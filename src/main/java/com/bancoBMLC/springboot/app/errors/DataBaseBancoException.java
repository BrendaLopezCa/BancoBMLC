package com.bancoBMLC.springboot.app.errors;

public class DataBaseBancoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataBaseBancoException() {
		super("Contacte con la administracion, hubo un error con la base de datos");
	}
	
}

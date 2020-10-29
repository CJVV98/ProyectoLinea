package com.udec.exception;



import io.swagger.annotations.ApiModel;

@ApiModel(description = "Excepcion: Peticion incorrecta")
public class ObjectFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public ObjectFoundException(String message) {
		super(message);
	}	
	
	

}


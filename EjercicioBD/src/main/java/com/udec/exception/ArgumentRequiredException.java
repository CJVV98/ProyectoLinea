package com.udec.exception;



import io.swagger.annotations.ApiModel;

@ApiModel(description = "Excepcion: Argumento requerido")
public class ArgumentRequiredException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ArgumentRequiredException(String message) {
		super(message);
	}
	
	
}


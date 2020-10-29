package com.udec.exception;



import io.swagger.annotations.ApiModel;

@ApiModel(description = "Excepcion: Recurso u objeto requerido")
public class ObjectRequiredException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
	public ObjectRequiredException(String mesage) {
	        super(mesage);
	}
	
	
}


package com.udec.exception;



import io.swagger.annotations.ApiModel;

@ApiModel(description = "Excepcion: recurso no encontrado")
public class ModelNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public ModelNotFoundException(String message) {
		super(message);
	}
}


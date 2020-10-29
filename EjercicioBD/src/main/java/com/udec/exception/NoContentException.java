package com.udec.exception;



import io.swagger.annotations.ApiModel;

@ApiModel(description = "Excepcion: No hay contenido")
public class NoContentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public NoContentException(String message) {
		super(message);
	}
	
}


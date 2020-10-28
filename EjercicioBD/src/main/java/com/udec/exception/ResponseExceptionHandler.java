package com.udec.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udec.dto.ErrorDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	// EXCEPCIONES PERSONALIZADAS
	@ExceptionHandler(value = { ModelNotFoundException.class })
	public final ResponseEntity<ErrorDto> filtroModelNotFoundException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { BussinesLogicException.class })
	public final ResponseEntity<ErrorDto> filtroBussinesLogicException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);

	}
	
	

	@ExceptionHandler(value = { ObjectFoundException.class })
	public final ResponseEntity<ErrorDto> filtroBadRequestException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), HttpStatus.BAD_REQUEST.name(), ex.getMessage(),
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { ArgumentRequiredException.class })
	public final ResponseEntity<ErrorDto> filtroArgumentRequiredException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { ObjectRequiredException.class })
	public final ResponseEntity<ErrorDto> filtroObjectRequiredException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { NoContentException.class })
	public final ResponseEntity<ErrorDto> filtroNoContentException(Exception ex, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(HttpStatus.NO_CONTENT.value()), HttpStatus.NO_CONTENT.name(),
				ex.getMessage(), request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.NO_CONTENT);
	}

	// EXCEPCIONES DE JAVA

	@ExceptionHandler(value = { NullPointerException.class })
	public final ResponseEntity<ErrorDto> filtroNullPointerException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "Objeto no inicializado",
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { ArithmeticException.class })
	public final ResponseEntity<ErrorDto> filtroArithmeticException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "Operación matematica incorrecta",
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { NumberFormatException.class })
	public final ResponseEntity<ErrorDto> filtroNumberFormatException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "Error en casteo de numero",
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { FileNotFoundException.class })
	public final ResponseEntity<ErrorDto> filtroFileNotFoundException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(),
				"Archivo incorrecto o no encontrado", request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { IOException.class })
	public final ResponseEntity<ErrorDto> filtroIOException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "El archivo contiene errores",
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

	@ExceptionHandler(value = { ArrayIndexOutOfBoundsException.class })
	public final ResponseEntity<ErrorDto> filtroArrayIndexException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(),
				"Error el indice es ilegal, ya que supera el tramaño de la matriz", request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	// *

	// EXCEPCIONES DE LOS SERVICIOS

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errores = new ArrayList<String>();
		for (FieldError errorDato : ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()) {
			errores.add(errorDato.getDefaultMessage());
		}
		for (ObjectError errorDato : ((MethodArgumentNotValidException) ex).getBindingResult().getGlobalErrors()) {
			errores.add(errorDato.getDefaultMessage());
		}
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), errores,
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	

	

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(),
				"Metodo de peticion invocado de manera incorrecta ", request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(),
				"El formato del cuerpo es incorrecto ", request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "Url o recurso no encontrado ",
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(),
				"El metodo invocado requiere de un cuerpo valido ", request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<Object>(error, status);
	}

	@ExceptionHandler(value = { Exception.class })
	public final ResponseEntity<ErrorDto> filtroException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}

}

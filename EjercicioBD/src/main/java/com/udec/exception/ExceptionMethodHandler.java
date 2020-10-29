package com.udec.exception;



import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.udec.dto.ErrorDto;

@ControllerAdvice
@RestController
public class ExceptionMethodHandler {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ErrorDto> filtroMethodArgumentTypeMismatchException(Exception ex, WebRequest request) {
		HttpStatus status=HttpStatus.METHOD_NOT_ALLOWED;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "Metodo invocado de manera incorrecta", request.getDescription(false));
		return new ResponseEntity<ErrorDto>(error, status);					
	}
	
	
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public final ResponseEntity<ErrorDto> filtroErrorValidacion(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<String> errores = new ArrayList<String>();
        for (ConstraintViolation<?> cv : ((ConstraintViolationException) ex).getConstraintViolations()) {
           errores.add(cv.getMessageTemplate());
        }
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), errores,
				request.getDescription(false));
		System.out.println(ex.getStackTrace());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	
}

package com.udec.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDto {
	
	@ApiModelProperty(notes = "Variable que contiene la fecha de la excepcion.", required = true, position = 0)
	private LocalDateTime timestamp;
	@ApiModelProperty(notes = "Variable que contiene el status de la excepcion.", required = true, position = 1)
	private String status;
	@ApiModelProperty(notes = "Variable que contiene en detalle el error de la excepcion.", required = true, position = 2)
	private String error;
	@ApiModelProperty(notes = "Variable que contiene el mensaje de la excepcion.", required = true, position = 3)
	private Object message;
	@ApiModelProperty(notes = "Variable que contiene el path de la excepcion.", required = true, position = 4)
	private String path;

	public ErrorDto(String status, String error, Object message, String path) {
		super();
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
package com.udec.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


public class AutorDto {

	private Integer id;	
	
	@NotNull(message="La cedula es un campo requerido.")
	@Pattern(regexp = "[0-9]*",message = "La cedula solo debe contener numeros.")
	@Size(min = 7,max = 11, message = "La cedula debe contener entre 7 y 11 digitos.")
	private String cedula;
	
	@NotNull(message="El nombre es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nombre solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El nombre debe ser mayor de 3 letras y no debe exceder 30.")
	private String nombre;
	
	@NotNull(message="El apellido es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nombre solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El apellido debe ser mayor de 3 letras y no debe exceder 30.")
	private String apellido;
		
	@NotNull(message="La fecha es requerida.")
	@Past(message = "La fecha debe ser menor a la fecha actual")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaNacimiento;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public AutorDto(Integer id,
			@NotNull(message = "La cedula es un campo requerido.") @Pattern(regexp = "[0-9]*", message = "La cedula solo debe contener numeros.") @Size(min = 7, max = 10, message = "La cedula debe contener entre 7 y 10 digitos.") String cedula,
			@NotNull(message = "El nombre es requerido.") @Pattern(regexp = "[a-zA-Z]*", message = "El nombre solo debe contener letras.") @Size(min = 3, max = 30, message = "El nombre debe ser mayor de 3 letras y no debe exceder 30.") String nombre,
			@NotNull(message = "El apellido es requerido.") @Pattern(regexp = "[a-zA-Z]*", message = "El nombre solo debe contener letras.") @Size(min = 3, max = 30, message = "El apellido debe ser mayor de 3 letras y no debe exceder 30.") String apellido,
			@NotNull(message = "La fecha es requerida.") @Past(message = "La fecha debe ser menor a la fecha actual") LocalDate fechaNacimiento
		) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		
	}

	public AutorDto() {
		super();
	}
	
	
	
}

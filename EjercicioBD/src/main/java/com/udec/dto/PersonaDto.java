package com.udec.dto;
import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Clase que contiene los atributos basicos de la persona.",value = "Persona")
public class PersonaDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message="El id es requerido.")
	private Integer id;
	@Size(min = 5,max = 8, message = "La cedula debe ser mayor de 10000 y no debe exceder 19999999.")
	@ApiModelProperty(notes = "Variable que contiene el numero de cedula.",required = true,example = "10000354",position = 0 )
	private String numCedula;
	@Size(min = 5,max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nombre solo debe contener letras.")
	@ApiModelProperty(notes = "Variable que contiene el nombre de la persona.",required = true,example = "Johans",position = 1)
	private String nombre;
	@Size(min = 5,max = 30, message = "El apellido debe ser mayor de 5 letras y no debe exceder 30.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El apellido solo debe contener letras.")
	@ApiModelProperty(notes = "Variable que contiene el apellido de la persona.",required = true,example = "Gonzales",position = 2)
	private String apellido;
	@Min(value = 18, message="La edad debe ser mayor a 18")
	@Max(value =30, message="La edad no puede superar el valor de 30")
	@ApiModelProperty(notes = "Variable que contiene la edad de la persona.",required = true,example = "31",position = 3)
	private int edad;
	@Size(min = 5,max = 30, message = "El correo debe ser mayor de 5 letras y no debe exceder 30.")
	@Email(message = "El correo debe tener el formato correcto. Ejemplo:abc@gmail.com")
	@ApiModelProperty(notes = "Variable que contiene el correo de la persona.",required = true,example = "johansagonzalez@ucundinamarca.edu.co",position = 2)
	private String correo;
	


	public PersonaDto(Integer id,
			@NotNull(message = "La cedula es requerida.") @Size(min = 5, max = 30, message = "La cedula debe ser mayor de 10000 letras y no debe exceder 19999999.") String numCedula,
			@NotNull(message = "El nombre es requerido.") @Size(min = 5, max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.") @Pattern(regexp = "[a-zA-Z]*", message = "El nombre solo debe contener letras.") String nombre,
			@NotNull(message = "El apellido es requerido.") @Size(min = 5, max = 30, message = "El apellido debe ser mayor de 5 letras y no debe exceder 30.") @Pattern(regexp = "[a-zA-Z]*", message = "El apellido solo debe contener letras.") String apellido,
			@NotNull(message = "La edad es requerida.") @Min(value = 18, message = "La edad debe ser mayor a 18") @Max(value = 30, message = "La edad no puede superar el valor de 30") int edad,
			@NotNull(message = "El correo es requerido.") @Size(min = 5, max = 30, message = "El correo debe ser mayor de 5 letras y no debe exceder 30.") @Pattern(regexp = "[a-zA-Z]*", message = "El correo solo debe contener letras.") String correo) {
		super();
		this.id = id;
		this.numCedula = numCedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.correo = correo;
	}


	
	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumCedula() {
		return numCedula;
	}


	public void setNumCedula(String numCedula) {
		this.numCedula = numCedula;
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


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public PersonaDto() {
		super();
	}


	
	
}

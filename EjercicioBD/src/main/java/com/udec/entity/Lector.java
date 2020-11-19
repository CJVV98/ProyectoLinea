package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name = "lector")
public class Lector {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;	
	
	@NotNull(message = "La cedula no puede ser vacia")
	@Size(min = 7,max = 11, message = "La c�dula del autor debe contener entre 7 y 11 car�cteres")	
	@Column(name = "cedula", nullable = false, length = 11)
	private String cedula;	
	
	@Column(name = "nombre", nullable = false)
	@NotNull(message="El nombre del autor es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nombre del autor solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El nombre del autor debe ser mayor de 3 letras y no debe exceder 30.")
	private String nombre;
	
	@Column(name = "apellido", nullable = false)
	@NotNull(message="El apellido del autor es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El apellido del autor solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El apellido del autor debe ser mayor de 3 letras y no debe exceder 30.")
	private String apellido;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
	
		

}

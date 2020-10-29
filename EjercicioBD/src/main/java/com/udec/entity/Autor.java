package com.udec.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "autor")
public class Autor {
	
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
		

	@Column(name = "fecha_nacimiento", nullable=false, columnDefinition = "Date")
	@NotNull(message="La fecha de nacimiento del autor es requerida.")
	@Past(message = "La fecha de nacimiento del autor debe ser menor a la fecha actual")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaNacimiento;
	

	@OneToMany(mappedBy = "autor", orphanRemoval=true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Libro> libros;


	@NotNull(message="La direccion es requerida")
	@OneToOne(mappedBy = "autor", orphanRemoval=true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Direccion direccion;
	

	

	public Autor(Integer id,
			@NotNull @Size(min = 7, max = 11, message = "La c�dula del autor debe contener entre 7 y 11 car�cteres") String cedula,
			@NotNull(message = "El nombre del autor es requerido.") @Pattern(regexp = "[a-zA-Z]*", message = "El nombre del autor solo debe contener letras.") @Size(min = 3, max = 30, message = "El nombre del autor debe ser mayor de 3 letras y no debe exceder 30.") String nombre,
			@NotNull(message = "El apellido del autor es requerido.") @Pattern(regexp = "[a-zA-Z]*", message = "El apellido del autor solo debe contener letras.") @Size(min = 3, max = 30, message = "El apellido del autor debe ser mayor de 3 letras y no debe exceder 30.") String apellido,
			@NotNull(message = "La fecha de nacimiento del autor es requerida.") @Past(message = "La fecha de nacimiento del autor debe ser menor a la fecha actual") LocalDate fechaNacimiento,
			List<Libro> libros, @NotNull(message = "La direccion es requerida") Direccion direccion) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.libros = libros;
		this.direccion = direccion;
	}

	public Autor() {
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

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

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	

	
}

package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	
	@NotNull(message = "La descripcion de la direccion del autor no puede ser vacia.")
	@Size(min = 7,max = 50, message = "La descripcion de la direccion del autor debe contener entre 7 y 50 caracteres")	
	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;
	
	@NotNull(message = "El barrio del autor no puede ser vacia.")
	@Size(min = 7,max = 50, message = "El barrio del autor debe contener entre 7 y 50 caracteres")	
	@Column(name = "barrio", nullable = false, length = 50)
	private String barrio;

	@OneToOne
	@MapsId
	private Autor autor;
	
	

	
	public Direccion() {
		super();
	}

	public Direccion(Integer id,
			@NotNull @Size(min = 3, max = 50, message = "La c�dula del autor debe contener entre 7 y 50 car�cteres") String descripcion,
			@NotNull @Size(min = 3, max = 50, message = "La c�dula del autor debe contener entre 7 y 50 car�cteres") String barrio,
			Autor autor) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.barrio = barrio;
		this.autor = autor;
	}

	@JsonIgnore
	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}


	
	
	
}

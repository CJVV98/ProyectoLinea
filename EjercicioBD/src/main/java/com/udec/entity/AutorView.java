package com.udec.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "autor_view")
public class AutorView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(insertable = false, updatable = false)
	private Integer id;	
	
	@Column(insertable = false, updatable = false)
	private String nombre;
	
	@Column(insertable = false, updatable = false)
	private String apellido;

	@Column(insertable = false, updatable = false)
	private String descripcion;
	
	@Column(insertable = false, updatable = false)
	private String barrio;
	
	@Column(insertable = false, updatable = false)
	private Integer cantidad;

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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

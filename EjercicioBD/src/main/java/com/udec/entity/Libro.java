package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", nullable = false)
	@NotNull(message="El nombre del libro es requerido.")
	@Size(min = 5, max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.")
	private String nombre;
	
	@Column(name = "numero_paginas", nullable = false)
	@NotNull(message="La cantidad de paginas es requerida.")
	@Min(value = 12, message="La cantidad de paginas debe ser mayor a  12 paginas")
	@Max(value =2000, message="La cantidad de paginas debe ser menor a 2000 paginas")
	private Integer numeroPaginas;
	
	@ManyToOne()
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "id_autor", nullable = false, foreignKey = @ForeignKey(name="FK_autor"))
	private Autor autor;	
		
	public Libro() {
	}
	
	
	public Libro(Integer id, String nombre, Integer numeroPaginas, Autor autor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
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
	
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	@JsonIgnore()
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
}

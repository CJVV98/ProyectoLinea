package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "autor_lector")
@IdClass(AutorLectorPk.class)
public class AutorLector {

	@Id
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Autor autor;
	
	@Id
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Lector lector;
	
	@Column(name="info_adicional", nullable = false, length = 25)
	private String infoAdicional;

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
	
	
}

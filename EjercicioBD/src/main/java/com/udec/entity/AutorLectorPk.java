package com.udec.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AutorLectorPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name= "id_autor", nullable=false)
	private Autor autor;
	
	@ManyToOne
	@JoinColumn(name= "id_lector", nullable=false)
	private Lector lector;
	
	
}

package com.udec.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import com.udec.entity.Autor;
import com.udec.entity.Lector;

public class AutorLectorDto implements Serializable {

	private AutorDto autor;
	private LectorDto lector;
	private String infoAdicional;




	public AutorDto getAutor() {
		return autor;
	}




	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}




	public LectorDto getLector() {
		return lector;
	}




	public void setLector(LectorDto lector) {
		this.lector = lector;
	}




	public String getInfoAdicional() {
		return infoAdicional;
	}




	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
	
	

}

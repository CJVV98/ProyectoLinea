package com.udec.services.interfaces;


public interface ICrudService <T, ID>{

	public void insertar(T objeto);
	public void editar(T objeto);
	public void eliminar(ID id);
}

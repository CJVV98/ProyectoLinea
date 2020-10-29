package com.udec.services.interfaces;


import org.springframework.data.domain.Page;

import com.udec.dto.AutorDto;
import com.udec.dto.LibroDto;
import com.udec.entity.Autor;
import com.udec.entity.AutorView;
import com.udec.entity.Direccion;
import com.udec.entity.Libro;

public abstract interface IAutorService extends ICrudService<Autor,Integer>{

	public void eliminarLibros(Integer id);
	public Page<Autor> listarNombreLibrosUno(String nombre, int page, int size) ;
	public Page<Autor> listarPaginado(Boolean lazy, Integer page, Integer size);	
	public Autor consultar(Boolean lazy, Integer id);	
	public void editarDireccion(Direccion direccion);
	public Page<Object[]> listarVistaAutores (int page, int size);
	public AutorView listarVistaAutor (Integer id);
}


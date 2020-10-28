package com.udec.services.interfaces;

import org.springframework.data.domain.Page;


import com.udec.entity.Libro;

public abstract interface ILibroService extends ICrudService<Libro,Integer>{
	
	public Page<Libro> listarPaginado(Integer page, Integer size);	
	public Libro consultar(Integer id);
	
}

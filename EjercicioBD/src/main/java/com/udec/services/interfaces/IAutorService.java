package com.udec.services.interfaces;


import java.util.List;

import org.springframework.data.domain.Page;

import com.udec.dto.AutorDto;
import com.udec.dto.AutorLectorDto;
import com.udec.dto.LibroDto;
import com.udec.entity.Autor;
import com.udec.entity.AutorLector;
import com.udec.entity.AutorView;
import com.udec.entity.Direccion;
import com.udec.entity.Libro;

public abstract interface IAutorService extends ICrudService<Autor,Integer>{

	public void eliminarLibros(Integer id);
	public Page<Autor> listarNombreLibrosUno(String nombre, int page, int size) ;
	public Page<Autor> listarPaginado(Boolean lazy, Integer page, Integer size);	
	public Autor consultar(Boolean lazy, Integer id);	
	public void editarDireccion(Direccion direccion);
	public Page<AutorView> listarVistaAutores (int page, int size);
	public AutorView listarVistaAutor (Integer id);
	public void guardarLector(AutorLectorDto lector);
	public Page<AutorLector> consultarLectores(Integer idAutor, int page, int size);
	public void guardarAutorLector(List<AutorLectorDto> autorLector);
}


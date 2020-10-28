package com.udec.services.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.entity.Libro;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BussinesLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAutorRepo;
import com.udec.repository.ILibroRepo;
import com.udec.services.interfaces.ILibroService;


@Service("Libro")
public class LibroServiceImp implements ILibroService {
	
	
	@Autowired
	ILibroRepo repo;
	
	@Autowired
	IAutorRepo repositoryAutor;

	@Override
	public Libro consultar( Integer id) {
		Libro libro = repo.findById(id).orElseThrow(() -> 
		new ModelNotFoundException("Libro no encontrado"));
		return libro;
	}

	
	@Override
	public void eliminar(Integer id) {
		if (!repo.existsById(id)) {
			throw new ModelNotFoundException("Libro no encontrado.");
		} else {
			repo.deleteById(id);
		}
		
	}

	@Override
	public Page<Libro> listarPaginado( Integer pagina, Integer tamanio) {
		Page<Libro> listaLibrosPaginado = repo.findAll(PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending()));;
		return listaLibrosPaginado;
	}

	@Override
	public void insertar(Libro libroSave) {
		if (libroSave.getAutor().getId() == null) {
			throw new ArgumentRequiredException("El id del autor es requerido");
		} else {			
			if (!repositoryAutor.existsById(libroSave.getAutor().getId())) {
				throw new ModelNotFoundException("Autor no encontrado.");
			}
		}	
		Integer datosLibro=repo.validarExistencia(libroSave.getNombre(), libroSave.getAutor().getId());
		if(datosLibro>0) {
			throw new BussinesLogicException("Ya existe un libro para ese autor");
		}
		repo.save(libroSave);
	}

	@Override
	public void editar(Libro libroEdit) {
		if (libroEdit.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido");
		}
		Libro libro = repo.findById(libroEdit.getId()).orElseThrow
				(() -> new ModelNotFoundException("El libro no existe."));
		
		if (libroEdit.getNombre() != null || libroEdit.getNombre() != "") {
			if(!libroEdit.getNombre().equalsIgnoreCase(libro.getNombre())){
				Integer datosLibro=repo.validarExistencia(libroEdit.getNombre(), libro.getAutor().getId());
				if(datosLibro>0) {
					throw new BussinesLogicException("Ya existe un libro para ese autor");
				}
			}
			libro.setNombre(libroEdit.getNombre());
		}		
		if (libroEdit.getNumeroPaginas() != null ) {
			libro.setNumeroPaginas(libroEdit.getNumeroPaginas());
		}	
		repo.save(libro);	
	}
	
}

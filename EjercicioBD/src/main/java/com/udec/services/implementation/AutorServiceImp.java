package com.udec.services.implementation;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.udec.dto.AutorLectorDto;
import com.udec.entity.Autor;
import com.udec.entity.AutorLector;
import com.udec.entity.AutorView;
import com.udec.entity.Direccion;
import com.udec.entity.Lector;
import com.udec.entity.Libro;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BussinesLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAutorLectorRepo;
import com.udec.repository.IAutorRepo;
import com.udec.repository.IAutorViewRepo;
import com.udec.repository.IDireccionRepo;
import com.udec.services.interfaces.IAutorService;



@Service("Autor")
public class AutorServiceImp implements IAutorService {
	@Autowired
	IAutorRepo repo;
	
	@Autowired
	IDireccionRepo repoDireccion;
	
	@Autowired
	IAutorViewRepo repovista;
	
	@Autowired
	IAutorLectorRepo repoAutorLector;


	@Override
	public void insertar(Autor objeto) {
		Integer contador  =repo.validarExistencia(objeto.getCedula());
		if(contador > 0)
			throw new BussinesLogicException("Ya existe un Autor con esta c�dula");	
		if(objeto.getLibros() != null) {
			for(Libro libro: objeto.getLibros()) {
				libro.setAutor(objeto);
			}		
		}
		if(objeto.getDireccion() != null) {
			objeto.getDireccion().setAutor(objeto);
		}
		repo.save(objeto);
	}


	@Override
	public Page<Autor> listarPaginado(Boolean lazy, Integer page, Integer size) {
		Page<Autor> autores = repo.findAll(PageRequest.of(page, size, Sort.by("nombre").ascending()));
		if (lazy) {
			for (Autor autor : autores) {
				autor.setLibros(null);
				autor.setDireccion(null);
			}
		}
		return autores;
	}

	
	@Override
	public void eliminar(Integer id) {
		Autor autorConsultado = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		if (autorConsultado.getLibros().size() > 0) {
			throw new BussinesLogicException("No puede eliminar el autor porque tiene libros asociados");
		}
		repo.deleteById(autorConsultado.getId());
	}

	@Override
	public Autor consultar(Boolean lazy, Integer id) {
		Autor autorConsultado = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		if(lazy) {
			autorConsultado.setLibros(null);
		}
		return autorConsultado;
	}

	
	
	@Override
	public void eliminarLibros(Integer id) {
		Autor autorConsultado = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		repo.deleteById(autorConsultado.getId());
	}

	

	@Override
	public Page<Autor> listarNombreLibrosUno(String nombre, int page, int size) {
		Page<Autor> listado = repo.findByLibros_NombreIgnoreCaseContaining(nombre,
				PageRequest.of(page, size, Sort.by("nombre").ascending()));
		for (Autor autor : listado) {
			autor.setLibros(null);
		}
		return listado;
	}

	@Override
	public void editar(Autor autor) {
		if (autor.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido");
		}
		Autor autoreditado= repo.findById(autor.getId()).orElseThrow(()->
		new ModelNotFoundException("Autor no encontrado"));
		Integer existe=repo.validarExistenciaID(autor.getCedula(), autor.getId());
		if(existe>0) {
			throw new BussinesLogicException("Un autor con ese numero de cedula ya se registro.");
		}
		autoreditado.setId(autor.getId());
		autoreditado.setCedula(autor.getCedula());
		autoreditado.setNombre(autor.getNombre());
		autoreditado.setApellido(autor.getApellido());
		autoreditado.setFechaNacimiento(autor.getFechaNacimiento());
		autoreditado.getDireccion().setDescripcion(autor.getDireccion().getDescripcion());
		autoreditado.getDireccion().setBarrio(autor.getDireccion().getBarrio());
		repo.save(autoreditado);
		
	}


	@Override
	public void editarDireccion(Direccion direccion) {
		if(direccion.getId() == null) {
			throw new ArgumentRequiredException("El id del autor debe ser obligatorio");
		}
		if(repoDireccion.existsById(direccion.getId()))
			repoDireccion.modificarDireccion(direccion.getBarrio(), direccion.getDescripcion(), direccion.getId());
		else
			throw new ModelNotFoundException("La direccion no existe");
		
	}


	@Override
	public Page<AutorView> listarVistaAutores(int page, int size) {
		Page<AutorView> listadoautores = repovista.findAll(PageRequest.of(page, size));
		System.out.println(listadoautores);
		return listadoautores;
	}


	@Override
	public AutorView listarVistaAutor(Integer id) {
		AutorView autorVista =  repovista.findById(id).get();
		if(autorVista == null) {
			throw new ModelNotFoundException("Autor no encontrado.");
		}
		
		return autorVista;
	}


	@Override
	public void guardarLector(AutorLectorDto autLector) {
		repoAutorLector.guardar(autLector.getAutor().getId(), autLector.getLector().getId(), autLector.getInfoAdicional());
	}


	@Override
	public Page<AutorLector> consultarLectores(Integer idAutor, int page, int size) {
		Page<AutorLector> listadoautores = repoAutorLector.listarLectoresAutor(idAutor, PageRequest.of(page, size));
		return listadoautores;
	}

    @Transactional
	@Override
	public void guardarAutorLector(List<AutorLectorDto> autorLector) {
		for(AutorLectorDto obj: autorLector) {
			repoAutorLector.guardar(obj.getAutor().getId(), obj.getLector().getId(), obj.getInfoAdicional());
		}
		
	}
	
	


}

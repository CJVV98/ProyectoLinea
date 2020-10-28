package com.udec.services.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.udec.entity.Autor;
import com.udec.entity.Libro;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BussinesLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAutorRepo;
import com.udec.services.interfaces.IAutorService;



@Service("Autor")
public class AutorServiceImp implements IAutorService {

	@Autowired
	IAutorRepo repo;

	
	@Override
	public void insertar(Autor objeto) {
		Integer contador  =repo.validarExistencia(objeto.getCedula());
		if(contador > 0)
			throw new BussinesLogicException("Ya existe un Autor con esta cédula");	
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


}

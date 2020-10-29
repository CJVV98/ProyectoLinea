package com.udec.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.entity.Autor;
import com.udec.entity.Persona;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BussinesLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IPersonaRepo;
import com.udec.services.interfaces.IPersonaService;


@Service("Persona")
public class PersonaServiceImp implements IPersonaService{

	@Autowired
	IPersonaRepo repository;

	
	@Override
	public void insertar(Persona persona) {
		Integer opc=repository.getNumCedula(persona.getNumCedula());
		if(opc==null || opc == 0) {
			Persona personas = repository.save(persona);
		}else {
			throw new BussinesLogicException("Una persona con ese numero de cedula ya se registro.");
		}		
	}
	
	@Override
	public Persona consultaCedula(String cedula) {
		List<Persona> listado= repository.buscarTodos(Sort.by(Sort.Direction.ASC,"numCedula"));
		Persona personaConsulta=listado.stream().filter(p -> p.getNumCedula().
				equalsIgnoreCase(cedula)).findFirst().orElseThrow(() -> new ModelNotFoundException("Persona no encontrada."));
		
		return personaConsulta;
	}

	@Override
	public void editar(Persona persona) {
		if (persona.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido");
		}
		String numCedula= persona.getNumCedula();
		Persona opc=repository.findByNumCedula(numCedula, Sort.by(Sort.Direction.ASC,"numCedula"));
		if(opc==null || (persona.getId()==opc.getId())) {
			Persona personas = repository.save(persona);
		}else {
			throw new BussinesLogicException("Una persona con ese numero de cedula ya se registro.");
		}
	}


	@Override
	public Persona consultar(Integer id) {
		// TODO Auto-generated method stub
		Persona opc= repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Persona no encontrada."));
		return opc;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		if (!repository.existsById(id)) {
			throw new ModelNotFoundException("La persona que desea eliminar no existe.");
		} else {
			repository.deleteById(id);
		}
		
	}

	@Override
	public List<Persona> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Page<Persona> listarPaginado(int page, int size) {
		return repository.findAll(PageRequest.of(page, size, Sort.by("nombre").ascending()));
		
		
	}



	
}
package com.udec.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udec.entity.Direccion;
import com.udec.entity.Libro;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BussinesLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IDireccionRepo;
import com.udec.services.interfaces.IDireccionService;


@Service("Direccion")
public class DireccionServiceImp implements IDireccionService{

	@Autowired
	IDireccionRepo repo;
	
	@Override
	public void editar(Direccion direccion) {
		if (direccion.getId() == null) {
			throw new ArgumentRequiredException("El id es requerido");
		}
		Direccion direccionConsulta = repo.findById(direccion.getId()).orElseThrow
				(() -> new ModelNotFoundException("La direccion no existe."));
		
		if (direccionConsulta.getDescripcion() != null || direccionConsulta.getDescripcion() != "") {
			direccionConsulta.setDescripcion(direccion.getDescripcion());
		}		
		if (direccionConsulta.getBarrio() != null || direccionConsulta.getBarrio()  != "") {
			direccionConsulta.setBarrio(direccion.getBarrio());
		}	
		repo.save(direccionConsulta);	
	}

	@Override
	public void editarQuery(Direccion direccion) {
		if(direccion.getId() == null) {
			throw new ArgumentRequiredException("El id del autor debe ser obligatorio");
		}
		if(repo.existsById(direccion.getId()))
			repo.modificarDireccion(direccion.getBarrio(), direccion.getDescripcion(), direccion.getId());
		else
			throw new ModelNotFoundException("La direccion no existe");
	}
	
}

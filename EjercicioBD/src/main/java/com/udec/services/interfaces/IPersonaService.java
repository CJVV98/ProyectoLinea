package com.udec.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.udec.entity.Autor;
import com.udec.entity.Persona;


@Service("Personas")
public interface IPersonaService extends ICrudService<Persona,Integer> {
	
	public Persona consultar(Integer id) ;
	public List<Persona> listar() ;
	public Page<Persona> listarPaginado(int page, int size) ; 
	public Persona consultaCedula(String cedula);
}

package com.udec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Integer>{
	
	@Query("SELECT count(p)  FROM Persona p WHERE p.numCedula = :numCedula")
	public Integer getNumCedula(@Param("numCedula") String numCedula);
	//HAcer segunda manera find
	//public Persona findByNumCedula(String numCedula);
	@Query("SELECT p  FROM Persona p")
	public List<Persona> buscarTodos(Sort sort);
	
	public Persona findByNumCedula(String numCedula, Sort sort);
	
	
	public Page<Persona> findByNombreIgnoreCase(@Param ("nombre") String nombre, Pageable pageable);
    
	@Query("SELECT p  FROM Persona p WHERE lower (p.nombre)= lower(:nombre) and lower (p.apellido)= lower(:apellido)")
	public Page<Persona> findByNombreApellido(@Param ("nombre") String nombre, @Param ("apellido") String apellido, Pageable pageable);
	
}

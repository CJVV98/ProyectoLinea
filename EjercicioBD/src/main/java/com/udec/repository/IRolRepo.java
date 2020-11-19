package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.udec.entity.Persona;
import com.udec.entity.Rol;

public interface IRolRepo extends JpaRepository<Rol, Integer>{
	
	@Query("SELECT count(r)  FROM Rol r WHERE r.id = :id")
	public Integer validarExistencia(@Param("id") Integer id);
}

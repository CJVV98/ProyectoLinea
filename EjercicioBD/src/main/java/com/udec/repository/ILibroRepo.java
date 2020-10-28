package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer> {

	@Query("SELECT count(l)  FROM Libro l WHERE lower(l.nombre) = lower(:nombre) and l.autor.id= :id ")
	public Integer validarExistencia(@Param("nombre") String nombre, @Param("id") Integer id );
	
}

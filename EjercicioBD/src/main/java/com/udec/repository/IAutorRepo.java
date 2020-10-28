package com.udec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Autor;
@Repository
public interface IAutorRepo  extends JpaRepository<Autor, Integer> {
	
	public Page<Autor> findByLibros_NombreIgnoreCaseContaining(@Param ("nombre") String nombre, Pageable pageable); 
	public Autor findById(Integer id, Sort sort);
	public Page<Autor> findByNombreIgnoreCase(@Param ("nombre") String nombre, Pageable pageable);
	
	@Query(value = "SELECT a FROM Autor a JOIN a.libros as libro  "
			+ " WHERE UPPER(libro.nombre) LIKE %:nombre% ")
	public Page<Autor> listarPorNombreLibro(@Param ("nombre")  String nombre, Pageable pageable);
	
	public Autor findByNombreAndApellido(@Param ("nombre") String nombre,@Param ("apellido") String apellido);
	
	@Query("SELECT count(a)  FROM Autor a WHERE a.cedula = :cedula and a.id != :id")
	public Integer validarExistenciaID(@Param("cedula") String numCedula, @Param("id") Integer id);
	
	
	@Query("SELECT count(a)  FROM Autor a WHERE a.cedula = :cedula ")
	public Integer validarExistencia(@Param("cedula") String numCedula);
	//@Query(value = "SELECT a.id, a.nombre, a.apellido, a.fecha_nacimiento FROM Autor a "
	//+ "JOIN Libro l ON a.id = l.id_autor WHERE lower(l.nombre) LIKE lower(:nombre) ", nativeQuery = true)
}



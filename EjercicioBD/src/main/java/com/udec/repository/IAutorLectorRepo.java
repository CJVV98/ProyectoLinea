package com.udec.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.udec.entity.Autor;
import com.udec.entity.AutorLector;
import com.udec.entity.Direccion;

public interface IAutorLectorRepo extends JpaRepository<AutorLector, Integer>{

	@Transactional
	@Modifying
	@Query(value="INSERT INTO autor_lector(id_autor, id_lector, info_adicional) "+
				" VALUES (:idAutor, :idLector, :infoAdicional)", nativeQuery = true)
	Integer guardar(@Param ("idAutor") Integer idAutor, @Param ("idLector") Integer idLector, @Param ("infoAdicional") String infoAdicional  );
	
	@Query(value="select al.lector from AutorLector al where al.autor.id = :idAutor ")
	public Page<AutorLector> listarLectoresAutor(@Param ("idAutor")  Integer idAutor, Pageable pageable);
	
}

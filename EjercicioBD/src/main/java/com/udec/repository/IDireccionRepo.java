package com.udec.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Autor;
import com.udec.entity.Direccion;

@Repository
public interface IDireccionRepo extends JpaRepository<Direccion, Integer>{
	
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE Direccion set barrio=:barrio, descripcion=:descripcion where autor_id = :autor_id",
	 nativeQuery = true)
	 void modificarDireccion(@Param("barrio") String barrio,@Param("descripcion") String descripcion, @Param("autor_id") Integer autor_id);
}

package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Persona;
import com.udec.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	Usuario findOneByNickIgnoreCaseContaining(String nick);
	@Query("SELECT count(u)  FROM Usuario u WHERE u.documento = :documento")
	public Integer validarExistencia(@Param("documento") String documento);
}

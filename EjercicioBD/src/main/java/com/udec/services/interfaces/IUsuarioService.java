package com.udec.services.interfaces;


import org.springframework.data.domain.Page;

import com.udec.entity.Autor;
import com.udec.entity.Usuario;

public interface IUsuarioService extends ICrudService<Usuario,Integer>{
	public Page<Usuario> listarPaginado( Integer page, Integer size);	
	public Usuario buscarNick(String nick);
	
}

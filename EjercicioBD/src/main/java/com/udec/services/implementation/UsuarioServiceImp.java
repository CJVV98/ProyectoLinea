package com.udec.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udec.entity.Autor;
import com.udec.entity.AutorView;
import com.udec.entity.Usuario;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.BussinesLogicException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IRolRepo;
import com.udec.repository.IUsuarioRepo;
import com.udec.services.interfaces.IUsuarioService;

@Service("Usuario")
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {

	@Autowired
	IUsuarioRepo repository;

	@Autowired
	IRolRepo repositoryRol;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public void insertar(Usuario objeto) {
		Usuario usuarioConsulta = repository.findOneByNickIgnoreCaseContaining(objeto.getNick());
		if (objeto.getRol() == null)
			throw new BussinesLogicException("Inserte un rol valido");
		if (usuarioConsulta != null)
			throw new BussinesLogicException("Este Nick ya esta registrado");
		Integer validacion = repository.validarExistencia(objeto.getDocumento());
		if (validacion > 0)
			throw new BussinesLogicException("Este documento ya esta registrado");
		Integer validarRol = repositoryRol.validarExistencia(objeto.getRol().getId());
		if (validarRol <= 0)
			throw new BussinesLogicException("Rol no encontrado");
		String clave=bcrypt.encode(objeto.getClave());
		objeto.setClave(clave);
		repository.save(objeto);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarios = repository.findOneByNickIgnoreCaseContaining(username);
		if (usuarios == null)
			throw new ModelNotFoundException("Usuario no encontrado");
		if (usuarios.isEstado() == false)
			throw new BussinesLogicException("Usuario deshabilitado");
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuarios.getRol().getNombre()));

		UserDetails detallesUsuario = new User(usuarios.getNick(), usuarios.getClave(), roles);

		return detallesUsuario;
	}

	@Override
	public void editar(Usuario objeto) {
		if (objeto.getIdUsuario() == null) {
			throw new ArgumentRequiredException("Id de usuario es requerido");
		}
		Usuario usuarioEditado=repository.findById(objeto.getIdUsuario()).orElseThrow(()->
								new ModelNotFoundException("Usuario no encontrado"));
		usuarioEditado.setApellido(objeto.getApellido());
		String clave=bcrypt.encode(objeto.getClave());
		usuarioEditado.setClave(clave);
		usuarioEditado.setDocumento(objeto.getDocumento());
		usuarioEditado.setNombre(objeto.getNombre());
		usuarioEditado.setEstado(objeto.isEstado());
		if(objeto.getRol() != null && objeto.getRol().getId() != null)
			usuarioEditado.setRol(objeto.getRol());
		repository.save(usuarioEditado);

	}

	@Override
	public void eliminar(Integer id) {
		Usuario usuarioConsultado = repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		repository.deleteById(usuarioConsultado.getIdUsuario());

	}

	@Override
	public Page<Usuario> listarPaginado(Integer page, Integer size) {
		Page<Usuario> listadoUsuarios = repository.findAll(PageRequest.of(page, size));
		return listadoUsuarios;
	}

	@Override
	public Usuario buscarNick(String nick) {
		return repository.findOneByNickIgnoreCaseContaining(nick);
	}

}

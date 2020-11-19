package com.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.entity.Autor;
import com.udec.entity.Usuario;
import com.udec.services.interfaces.IAutorService;
import com.udec.services.interfaces.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('administrador')")
@Validated
@RestController
@Api(description = "Todos los servicios transaccionales que se pueden realizar sobre un Usuario.",tags = "Servicios rest   ")
@RequestMapping("/usuarios/")
@ApiResponses(value={@ApiResponse(code = 200, message = "Transaccion exitosa"),@ApiResponse(code = 403, message = "Acceso prohibido"),@ApiResponse(code = 401, message = "Metodo no autorizado"),@ApiResponse(code = 404, message = "Recurso no encontrado")})
public class UsuarioController {


	@Autowired
	@Qualifier("Usuario")
	IUsuarioService service;
	
	@PostMapping("/insertar")
	@ApiOperation(value = "Insertar Usuario", notes = "El metodo que crea una nueva Usuario.",response = Usuario.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	public ResponseEntity<Object> insertar(@Valid @RequestBody Usuario usuario){
		service.insertar(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	

	@ApiOperation(value = "Listar Autors con paginado", notes = "El metodo que lista a los Autors.",response = List.class)
	@GetMapping("/listar/{page}/{size}")
	public ResponseEntity<Page<Usuario>> obtenerPage( @PathVariable int page, @PathVariable int size){
		Page<Usuario> users = service.listarPaginado(page, size);
		return new ResponseEntity<Page<Usuario>>(users, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Editar Usuarios", notes = "El metodo que edita un Usuario.",response = Usuario.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto editado")})
	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Usuario usuario){
		service.editar(usuario);
		return new ResponseEntity<Object>( HttpStatus.OK);
	}
	
	@ApiOperation(value = "Editar Usuarios", notes = "El metodo que edita un Usuario.",response = Usuario.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto editado")})
	@PutMapping("/eliminar")
	public ResponseEntity<Void> eliminar(@Valid @NonNull @PathVariable Integer id){
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	

	
	
}

package com.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.dto.AutorDto;
import com.udec.entity.Autor;
import com.udec.services.interfaces.IAutorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@Validated
@RestController
@Api(description = "Todos los servicios transaccionales que se pueden realizar sobre una Autor.",tags = "Servicios rest   ")
@RequestMapping("/autores/")
@ApiResponses(value={@ApiResponse(code = 200, message = "Transaccion exitosa"),@ApiResponse(code = 403, message = "Acceso prohibido"),@ApiResponse(code = 401, message = "Metodo no autorizado"),@ApiResponse(code = 404, message = "Recurso no encontrado")})
public class AutorController {
	

	@Autowired
	@Qualifier("Autor")
	IAutorService service;
	

	
	@ApiOperation(value = "Listar Autors con paginado", notes = "El metodo que lista a las Autors.",response = List.class)
	@GetMapping("/listarPaginador/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Autor>> obtenerPage(@PathVariable boolean lazy, @PathVariable int page, @PathVariable int size){
		Page<Autor> Autors = service.listarPaginado(lazy, page, size);
		return new ResponseEntity<Page<Autor>>(Autors, HttpStatus.OK);
	}

	@ApiOperation(value = "Consultar Autor", notes = "El metodo que consulta una Autor por su cedula.",response = Autor.class)
	@GetMapping("/consultar/{lazy}/{id}")
	public ResponseEntity<Autor> consultarId(@Valid @NonNull @PathVariable boolean lazy, @NonNull @PathVariable Integer id){
		Autor Autors = service.consultar(lazy,id);
		return new ResponseEntity<Autor>(Autors, HttpStatus.OK);
	}
	
	

	
	@PostMapping("/crear")
	@ApiOperation(value = "Crear Autor", notes = "El metodo que crea una nueva Autor.",response = Autor.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	public ResponseEntity<Object> insertar(@Valid @RequestBody Autor autor){
		service.insertar(autor);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Editar Autor", notes = "El metodo que edita una Autor.",response = Autor.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Autor autor){
		service.editar(autor);
		return new ResponseEntity<Object>( HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value = "Eliminar Autor", notes = "El metodo que elimina una Autor por su cedula.")
	@ApiResponses(value={@ApiResponse(code = 204, message = "No hay contenido")})
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminar(@Valid @NonNull @PathVariable Integer id){
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Eliminar Autor con datos libro", notes = "El metodo que elimina una Autor por su cedula.")
	@ApiResponses(value={@ApiResponse(code = 204, message = "No hay contenido")})
	@DeleteMapping("/eliminar_libros/{id}")
	public ResponseEntity<Void> eliminarLibros(@Valid @NonNull @PathVariable Integer id){
		service.eliminarLibros(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Listar Autores por nombre", notes = "El metodo que lista los autores de un libro.",response = List.class)
	@GetMapping("/listarLibrosNombre/{nombre}/{page}/{size}")
	public ResponseEntity<Page<Autor>> listarLibrosUno(@PathVariable String nombre, @PathVariable int page, @PathVariable int size){
		Page<Autor> Autors = service.listarNombreLibrosUno(nombre, page, size);
		return new ResponseEntity<Page<Autor>>(Autors, HttpStatus.OK);
	}
	
	

	
}
	



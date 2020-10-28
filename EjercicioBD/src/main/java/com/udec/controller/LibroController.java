package com.udec.controller;



import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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


import com.udec.entity.Libro;
import com.udec.services.interfaces.ILibroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@RestController
@RequestMapping("/libros/")
@ApiResponses(value={@ApiResponse(code = 200, message = "Transaccion exitosa"),@ApiResponse(code = 403, message = "Acceso prohibido"),@ApiResponse(code = 401, message = "Metodo no autorizado"),@ApiResponse(code = 404, message = "Recurso no encontrado")})
public class LibroController {
	
	@Autowired
	ILibroService service;

	@ApiOperation(value = "Listar libros", notes = "El metodo que consulta varios libros.",response = Libro.class)
	@GetMapping("/listarPaginado/{pagina}/{tamanio}")
	public  ResponseEntity<Page<Libro>> listadoPaginado( @PathVariable Integer pagina, @PathVariable Integer tamanio) {		
		Page<Libro> listarLibro = service.listarPaginado(pagina, tamanio);
		return new ResponseEntity<Page<Libro>>(listarLibro, HttpStatus.OK);
	}
	

	@ApiOperation(value = "Consultar libro", notes = "El metodo que consulta un libro.",response = Libro.class)
	@GetMapping("/consultar/{id}")
	public ResponseEntity<Libro> consultarId(@Valid @NonNull @PathVariable Integer id){
		Libro Libros = service.consultar(id);
		return new ResponseEntity<Libro>(Libros, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Insertar libro", notes = "El metodo que crea una nuevo libro.",response = Libro.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PostMapping("/crear")
	public ResponseEntity<Object> insertar(@Valid @RequestBody Libro libro){
		service.insertar(libro);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Editar libro", notes = "El metodo que edita un libro.",response = Libro.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PutMapping("/editar")
	public ResponseEntity<Object>  editar(@Valid @RequestBody Libro Libro){
		service.editar(Libro);
		return new ResponseEntity<Object>( HttpStatus.OK);
	}
	
	@ApiOperation(value = "Eliminar libro", notes = "El metodo que elimina un libro.")
	@ApiResponses(value={@ApiResponse(code = 204, message = "No hay contenido")})
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminar(@Valid @NonNull @PathVariable Integer id){
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}

package com.udec.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.dto.PersonaDto;
import com.udec.entity.Persona;
import com.udec.services.interfaces.IPersonaService;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@RestController
@Api(description = "Todos los servicios transaccionales que se pueden realizar sobre una persona.",tags = "Servicios rest   ")
@RequestMapping("/personas/")
@ApiResponses(value={@ApiResponse(code = 200, message = "Transaccion exitosa"),@ApiResponse(code = 403, message = "Acceso prohibido"),@ApiResponse(code = 401, message = "Metodo no autorizado"),@ApiResponse(code = 404, message = "Recurso no encontrado")})
public class PersonaController {
	
	@Autowired
	@Qualifier("Persona")
	IPersonaService service;

	
	
	@ApiOperation(value = "Listar personas", notes = "El metodo que lista a las personas.",response = List.class)
	@GetMapping("/listar")
	public ResponseEntity<List<Persona>> get(){
		List<Persona> personas = service.listar();
		return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Listar personas con paginado", notes = "El metodo que lista a las personas.",response = List.class)
	@GetMapping("/listarPaginador/{page}/{size}")
	public ResponseEntity<Page<Persona>> getPage(@PathVariable int page, @PathVariable int size){
		Page<Persona> personas = service.listarPaginado(page, size);
		return new ResponseEntity<Page<Persona>>(personas, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Consultar persona", notes = "El metodo que consulta una persona por su cedula.",response = PersonaDto.class)
	@GetMapping("/consultar/{id}")
	public ResponseEntity<Persona> getId(@Valid @NonNull @PathVariable Integer id){
		Persona personas = service.consultar(id);
		return new ResponseEntity<Persona>(personas, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Crear persona", notes = "El metodo que crea una nueva persona.",response = PersonaDto.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PostMapping("/insertar")
	public ResponseEntity<Persona> save(@Valid @RequestBody Persona personas){
		System.out.println("La fecha es :" + personas.getFecha());
		service.insertar(personas);
		return new ResponseEntity<Persona>(HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Editar persona", notes = "El metodo que edita una persona.",response = PersonaDto.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PutMapping("/editar")
	public ResponseEntity<Persona> edit(@Valid @RequestBody Persona personas){
		service.editar(personas);
		return new ResponseEntity<Persona>( HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Eliminar persona", notes = "El metodo que elimina una persona por su cedula.")
	@ApiResponses(value={@ApiResponse(code = 204, message = "No hay contenido")})
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> delete(@Valid @NonNull @PathVariable Integer id){
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Consultar persona", notes = "El metodo que consulta una persona por su cedula.",response = PersonaDto.class)
	@GetMapping("/consultar_cedula/{cedula}")
	public ResponseEntity<Persona> getCedula(@Valid @NonNull @PathVariable String cedula){
		Persona personas = service.consultaCedula(cedula);
		return new ResponseEntity<Persona>(personas, HttpStatus.OK);
	}
	
	
}

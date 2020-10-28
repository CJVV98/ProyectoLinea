package com.udec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.entity.Autor;
import com.udec.entity.Direccion;
import com.udec.services.interfaces.IAutorService;
import com.udec.services.interfaces.IDireccionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Validated
@RestController
@Api(description = "Todos los servicios transaccionales que se pueden realizar sobre una Direccion.",tags = "Servicios rest   ")
@RequestMapping("/direccion/")
@ApiResponses(value={@ApiResponse(code = 200, message = "Transaccion exitosa"),@ApiResponse(code = 403, message = "Acceso prohibido"),@ApiResponse(code = 401, message = "Metodo no autorizado"),@ApiResponse(code = 404, message = "Recurso no encontrado")})
public class DireccionController {
	
	@Autowired
	IDireccionService service;
	
	@ApiOperation(value = "Editar Autor", notes = "El metodo que edita una Autor.",response = Autor.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Direccion direccion){
		service.editar(direccion);
		return new ResponseEntity<Object>( HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Editar Autor", notes = "El metodo que edita una Autor.",response = Autor.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Objeto creado")})
	@PutMapping("/editarQuery")
	public ResponseEntity<Object> editarQuery(@Valid @RequestBody Direccion direccion){
		service.editarQuery(direccion);
		return new ResponseEntity<Object>( HttpStatus.OK);
	}
}

package com.udec.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class LibroDto {	
	
	private Integer id;

	@NotNull(message="El nombre es requerido.")
	@Size(min = 5,max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.")
	private String nombre;
	
	@NotNull(message="La cantidad de paginas es requerida.")
	@Min(value = 12, message="Debe ser mayor a  12 paginas")
	@Max(value =2000, message="Debe ser menor a 2000 paginas")
	private Integer numeroPaginas;
	
	private AutorDto autor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}

	public LibroDto(Integer id,
			@NotNull(message = "El nombre es requerido.") @Size(min = 5, max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.") String nombre,
			@NotNull(message = "La cantidad de paginas es requerida.") @Min(value = 12, message = "Debe ser mayor a  12 paginas") @Max(value = 2000, message = "Debe ser menor a 2000 paginas") Integer numeroPaginas,
			AutorDto autor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
	}

	public LibroDto() {
		super();
	}
	
	
}

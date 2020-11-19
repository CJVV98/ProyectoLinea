package com.udec.entity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "persona")
@ApiModel(description = "Clase que contiene los atributos basicos de la persona.",value = "Persona")
public class Persona {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Variable que contiene el id.",required = true,example = "1",position = 0 )
	private Integer id;
	
	@Column(name = "numCedula", nullable = false, unique = true)
	@Size(min = 7,max = 9, message = "La cedula debe ser mayor de 1000000 y no debe exceder 999999999.")
	@Pattern(regexp = "[0-9]*",message = "La cedula solo acepta numeros.")
	@NotNull(message = "El numero de cedula es obligatorio.")
	@ApiModelProperty(notes = "Variable que contiene el numero de cedula.",required = true,example = "10000354",position = 1 )
	private String numCedula;
	
	@Column(name = "nombre", nullable = false)
	@NotNull(message="El nombre es requerido.")
	@Size(min = 5,max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nombre solo debe contener letras.")
	@ApiModelProperty(notes = "Variable que contiene el nombre de la persona.",required = true,example = "Johans",position = 2)
	private String nombre;
	
	@Column(name = "apellido", nullable = false)
	@NotNull(message="El apellido es requerido.")
	@Size(min = 5,max = 30, message = "El apellido debe ser mayor de 5 letras y no debe exceder 30.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El apellido solo debe contener letras.")
	@ApiModelProperty(notes = "Variable que contiene el apellido de la persona.",required = true,example = "Gonzales",position = 3)
	private String apellido;
		
	@Column(name = "edad", nullable = false)
	@NotNull(message="La edad es requerida.")
	@Min(value = 18, message="La edad debe ser mayor a 18")
	@Max(value =30, message="La edad no puede superar el valor de 30")
	@ApiModelProperty(notes = "Variable que contiene la edad de la persona.",required = true,example = "31",position = 4)
	private Integer edad;
	
	@Column(name = "correo", nullable = false)
	@NotNull(message="El correo es requerido.")
	@Size(min = 5,max = 30, message = "El correo debe ser mayor de 5 letras y no debe exceder 30.")
	@Email(message = "El correo debe tener el formato correcto. Ejemplo:abc@gmail.com")
	@ApiModelProperty(notes = "Variable que contiene el correo de la persona.",required = true,example = "jgonzales@udec.com",position = 5)
	private String correo;
	
	@Column(name = "fecha", nullable=false, columnDefinition = "Date")
	@NotNull(message="La fecha es requerida.")
	@Past(message = "La fecha debe ser menor a la fecha actual")
	@DateTimeFormat(iso = ISO.DATE)
	@ApiModelProperty(notes = "Variable que contiene la fecha de naciemiento de la persona.",required = true,example = "jgonzales@udec.com",position = 6)
	private LocalDate fecha;
	
	

	public Persona() {
		super();
	}

	public Persona(Integer id,
			@Size(min = 5, max = 8, message = "La cedula debe ser mayor de 10000 y no debe exceder 19999999.") @NotNull(message = "El numero de cedula es obligatorio.") String numCedula,
			@NotNull(message = "El nombre es requerido.") @Size(min = 5, max = 30, message = "El nombre debe ser mayor de 5 letras y no debe exceder 30.") @Pattern(regexp = "[a-zA-Z]*", message = "El nombre solo debe contener letras.") String nombre,
			@NotNull(message = "El apellido es requerido.") @Size(min = 5, max = 30, message = "El apellido debe ser mayor de 5 letras y no debe exceder 30.") @Pattern(regexp = "[a-zA-Z]*", message = "El apellido solo debe contener letras.") String apellido,
			@NotNull(message = "La edad es requerida.") @Min(value = 18, message = "La edad debe ser mayor a 18") @Max(value = 30, message = "La edad no puede superar el valor de 30") Integer edad,
			@NotNull(message = "El correo es requerido.") @Size(min = 5, max = 30, message = "El correo debe ser mayor de 5 letras y no debe exceder 30.") @Email(message = "El correo debe tener el formato correcto. Ejemplo:abc@gmail.com") String correo,
			@NotNull(message = "La fecha es requerida.") @Past LocalDate fecha) {
		super();
		this.id = id;
		this.numCedula = numCedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.correo = correo;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumCedula() {
		return numCedula;
	}

	public void setNumCedula(String numCedula) {
		this.numCedula = numCedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	


	

}

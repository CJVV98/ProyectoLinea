package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "documento", nullable = false)
	@NotNull(message="El nombre del autor es requerido.")
	@Pattern(regexp = "[0-9]*",message = "El documento del usuario solo debe contener letras.")
	@Size(min = 7,max = 12, message = "El documento del usuario debe ser mayor de 7 letras y no debe exceder 12.")
	private String documento;
	
	@Column(name = "nombre", nullable = false)
	@NotNull(message="El nombre del autor es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nombre del usuario solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El nombre del usuario debe ser mayor de 3 letras y no debe exceder 30.")
	private String nombre;
	
	@Column(name = "apellido", nullable = false)
	@NotNull(message="El apellido del usuario es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El apellido del usuario solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El apellido del usuario debe ser mayor de 3 letras y no debe exceder 30.")
	private String apellido;
	
	@Column(name = "nick", nullable = false)
	@NotNull(message="El nick del usuario es requerido.")
	@Pattern(regexp = "[a-zA-Z]*",message = "El nick del usuario solo debe contener letras.")
	@Size(min = 3,max = 30, message = "El nick del usuario debe ser mayor de 3 letras y no debe exceder 30.")
	private String nick;
	
	
	@Column(name = "clave", nullable = false, columnDefinition = "TEXT")
	@NotNull(message="La clave del usuario es requerido.")
	private String clave;
	
	@Column(name = "estado", nullable = false)
	@NotNull(message="La estado del usuario es requerido.")
	private boolean estado;
	
	@ManyToOne()
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "id", nullable = false, foreignKey = @ForeignKey(name="FK_rol"))
	private Rol rol;
	
	
	

	public Usuario() {
		super();
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}
	

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}	
	
}

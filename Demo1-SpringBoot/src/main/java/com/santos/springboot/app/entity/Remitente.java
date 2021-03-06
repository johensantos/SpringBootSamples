package com.santos.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;




@Entity
@Table(name = "remitentes")
public class Remitente implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
	private long idRemitente;
	
	@NotEmpty
	@Column(name="nombre")
	private String nombreRemitente;
	@NotEmpty
	private String direccion;
	@NotEmpty
	//@Size(min = 6,max = 6)
	private String ubigeo;
	@NotEmpty
	private String telefono;
	@NotEmpty
	@Email
	private String correo;
	@NotEmpty
	//@Size(min = 11,max = 11)
	private String ruc;

	
	
	public Remitente() {
		
	}

	public Remitente(long idRemitente, String nombreRemitente, String direccion, String ubigeo, String telefono,
			String correo, String ruc) {
	
		this.idRemitente = idRemitente;
		this.nombreRemitente = nombreRemitente;
		this.direccion = direccion;
		this.ubigeo = ubigeo;
		this.telefono = telefono;
		this.correo = correo;
		this.ruc = ruc;
	}

	public long getIdRemitente() {
		return idRemitente;
	}

	public void setIdRemitente(long idRemitente) {
		this.idRemitente = idRemitente;
	}

	public String getNombreRemitente() {
		return nombreRemitente;
	}

	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	
	
}

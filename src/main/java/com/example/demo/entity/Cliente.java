package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	public Integer id;

	@Column(name = "nombre")
	public String nombre;

	@Column(name = "apellido")
	public String apellido;

	@Column(name = "direccion")
	public String direccion;

	@Column(name = "ciudad")
	public String ciudad;

	@Column(name = "pais")
	public String pais;

	@Column(name = "telefono")
	public String telefono;

	@Column(name = "email")
	public String email;

	@Column(name = "fecha_nacimiento")
	public Date fechaNacimiento;

	@Column(name = "tipo_documento")
	public String tipoDocumento;

	@Column(name = "numero_documento")
	public String numeroDocumento;

	@Column(name = "fecha_creacion", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date fechaCreacion;
}

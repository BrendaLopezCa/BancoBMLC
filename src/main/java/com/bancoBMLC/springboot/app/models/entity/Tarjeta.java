package com.bancoBMLC.springboot.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tarjetas")
public class Tarjeta {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarjeta;
	
	@Column(name = "numero_tarjeta", nullable = false, length = 16)
	@NotEmpty
	@Size(max = 18)
	private String numeroTarjeta;
	
	@Column(name = "icv", nullable = false, length = 3)
	@NotEmpty
	@Size(max = 3)
	private String icv;
	
	@Column(name = "nombre")
	@NotEmpty
	@Size(min = 3, max = 35)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "tarjeta_cuenta")
	private Cuenta cuenta;
	
	public String getCuenta() {
		return cuenta.getNumeroDeCuenta();
	}

	public void setCuenta(String numeroDeCuenta) {
		this.cuenta.setNumeroDeCuenta(numeroDeCuenta);
	}

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

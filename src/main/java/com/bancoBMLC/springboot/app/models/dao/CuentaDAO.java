package com.bancoBMLC.springboot.app.models.dao;

import java.util.List;

import com.bancoBMLC.springboot.app.models.entity.Cuenta;
import com.bancoBMLC.springboot.app.models.entity.Tarjeta;

public interface CuentaDAO {
	public List<Cuenta> findAll();
	
	public void save(Cuenta cuenta);

	public Tarjeta findOne(Long id);

	public void delete(Long id);

	
}

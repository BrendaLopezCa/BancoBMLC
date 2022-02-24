package com.bancoBMLC.springboot.app.models.dao;

import java.util.List;

import com.bancoBMLC.springboot.app.models.entity.Tarjeta;

public interface TarjetaDAO {
	public List<Tarjeta> findAll();
	
	public void save(Tarjeta tarjeta);

	public Tarjeta findOne(Long id);
	
	public void delete(Long id);
}

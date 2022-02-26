package com.bancoBMLC.springboot.app.models.dao;

import java.util.List;

import com.bancoBMLC.springboot.app.models.entity.Banco;

public interface BancoDAO {
	public List<Banco> findAll();
	
	public void save(Banco banco);

	public Banco findOne(Long id);
	
	public void delete(Long id);
}

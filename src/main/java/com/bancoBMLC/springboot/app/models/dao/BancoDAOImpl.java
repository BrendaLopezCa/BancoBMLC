package com.bancoBMLC.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoBMLC.springboot.app.models.entity.Banco;

@Repository
public class BancoDAOImpl implements BancoDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Banco> findAll() {
		return em.createQuery("from Banco").getResultList();
	}

	@Override
	@Transactional
	public void save(Banco banco) {

	}

}

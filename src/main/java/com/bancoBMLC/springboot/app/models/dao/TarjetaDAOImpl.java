package com.bancoBMLC.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoBMLC.springboot.app.errors.DataBaseBancoException;
import com.bancoBMLC.springboot.app.models.entity.Tarjeta;

@Repository
public class TarjetaDAOImpl implements TarjetaDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarjeta").getResultList();
	}

	@Override
	@Transactional
	public void save(Tarjeta tarjeta) throws DataBaseBancoException{
		if(tarjeta.getIdTarjeta() != null && tarjeta.getIdTarjeta() > 0) {
			try {
				em.merge(tarjeta);
			} catch (DataException e) {
				throw new DataBaseBancoException();
			}
		} else {
			try {
				em.persist(tarjeta);
			} catch (DataException e) {
				throw new DataBaseBancoException();
			}
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Tarjeta findOne(Long idTarjeta) {
		return em.find(Tarjeta.class, idTarjeta);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}
}

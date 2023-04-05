package it.prova.proprietariojpa.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.proprietariojpa.model.Automobile;
import it.prova.proprietariojpa.model.Proprietario;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from automobile", Automobile.class).getResultList();

	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);

	}

	@Override
	public void update(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		automobileInstance = entityManager.merge(automobileInstance);

	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(automobileInstance);

	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(automobileInstance));

	}

	@Override
	public List<Automobile> findErrori() throws Exception {
		TypedQuery<Automobile> query = entityManager.createQuery(
				"select distinct a from Automobile a inner join  a.proprietario p where a.proprietario.dataDinascita <18", Automobile.class);
		
		

		// return query.getSingleResult() ha il problema che se non trova elementi
		// lancia NoResultException
		return query.getResultList();
	}

}

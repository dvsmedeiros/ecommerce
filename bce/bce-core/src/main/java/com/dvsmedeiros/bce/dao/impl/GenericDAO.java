package com.dvsmedeiros.bce.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.bce.dao.ISpecificDAO;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Repository
@SuppressWarnings("unchecked")
public class GenericDAO<T extends DomainEntity> implements IDAO<T>, ISpecificDAO<T> {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void save(T aEntity) {
		em.merge(aEntity);
	}

	@Override
	public void update(T aEntity) {
		em.merge(aEntity);
	}

	@Override
	public void delete(T aEntity) {
		Class<? extends T> clazz = (Class<? extends T>) aEntity.getClass();
		T toDelete = find(aEntity.getId(), clazz);
		em.remove(toDelete);
	}

	@Override
	public List<T> findAll(Class<? extends T> clazz) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		
		return em.createQuery(jpql.toString()).getResultList();
	}
	
	@Override
	public T find(Long id, Class<? extends T> clazz) {
		return em.find(clazz, id);
	}

	@Override
	public T find(String code, Class<? extends DomainSpecificEntity> clazz) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append("WHERE e.code = :code ");
		jpql.append("AND e.active = 1");

		Query query = em.createQuery(jpql.toString());

		query.setParameter("code", code);

		List<T> result = query.getResultList();

		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public void delete(String code, Class<? extends DomainSpecificEntity> clazz) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("UPDATE ");
		jpql.append(clazz.getName());
		jpql.append(" e SET e.active = 0");
		jpql.append("WHERE e.code = :code");
		
		Query query = em.createQuery(jpql.toString());

		query.setParameter("code", code);
		query.executeUpdate();
		
	}
	
	@Override
    public List<T> findAll(boolean active, Class<? extends DomainSpecificEntity> clazz) {
        
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e FROM ");
        jpql.append(clazz.getName());
        jpql.append(" e ");
        jpql.append(active ? " WHERE e.active = 1" : "");
        
        return em.createQuery(jpql.toString()).getResultList();
    }

}

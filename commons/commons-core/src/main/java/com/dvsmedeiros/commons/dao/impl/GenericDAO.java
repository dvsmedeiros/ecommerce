package com.dvsmedeiros.commons.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.commons.domain.DomainEntity;

@Repository
@SuppressWarnings("unchecked")
public class GenericDAO<T extends DomainEntity> implements IDAO<T> {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void save(T aEntity) {
		em.persist(aEntity);
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
}

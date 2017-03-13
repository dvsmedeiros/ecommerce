package com.dvsmedeiros.commons.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.commons.dao.ISpecificDAO;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

@Repository
@SuppressWarnings("unchecked")
public class GenericSpecificDAO<T extends DomainSpecificEntity> extends GenericDAO<T> implements ISpecificDAO<T> {

//	@Override
//	public T find(String code, Class<? extends T> clazz) {
//
//		StringBuilder jpql = new StringBuilder();
//		jpql.append("SELECT e FROM ");
//		jpql.append(clazz.getName());
//		jpql.append(" e ");
//		jpql.append("WHERE e.code = :code ");
//		jpql.append("AND e.active = 1");
//
//		Query query = em.createQuery(jpql.toString());
//
//		query.setParameter("code", code);
//
//		List<T> result = query.getResultList();
//
//		if (result != null && !result.isEmpty()) {
//			return result.get(0);
//		}
//		return null;
//	}
//	
//	@Override
//	public void delete(String code, Class<? extends T> clazz) {
//		
//		StringBuilder jpql = new StringBuilder();
//		jpql.append("UPDATE ");
//		jpql.append(clazz.getName());
//		jpql.append(" e SET e.active = 0");
//		jpql.append("WHERE e.code = :code");
//		
//		Query query = em.createQuery(jpql.toString());
//
//		query.setParameter("code", code);
//		query.executeUpdate();
//		
//	}
//	
//	@Override
//    public List<T> findAll(boolean active, Class<? extends T> clazz) {
//        
//        StringBuilder jpql = new StringBuilder();
//        jpql.append("SELECT e FROM ");
//        jpql.append(clazz.getName());
//        jpql.append(" e ");
//        jpql.append(active ? " WHERE e.active = 1" : "");
//        
//        return em.createQuery(jpql.toString()).getResultList();
//    }
	
}

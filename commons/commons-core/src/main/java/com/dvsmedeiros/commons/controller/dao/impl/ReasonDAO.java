package com.dvsmedeiros.commons.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.dao.IReasonDAO;
import com.dvsmedeiros.commons.domain.Reason;

@Component
public class ReasonDAO extends GenericDAO<Reason> implements IReasonDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Reason> filter(Filter<Reason> filter) {
	
		boolean validFilter = filter != null && filter.getEntity() != null;
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT r FROM ").append(Reason.class.getName()).append(" r ");
		
		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getCode() != null) {
			jpql.append(" AND r.code = :code");
		}

		TypedQuery<Reason> query = em.createQuery(jpql.toString(), Reason.class);

		if (validFilter && filter.getEntity().getCode() != null) {
			query.setParameter("code", filter.getEntity().getCode());
		}
		
		return query.getResultList();
	
	}

}

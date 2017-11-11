package com.dvsmedeiros.commons.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.dao.ICupomDAO;
import com.dvsmedeiros.commons.domain.Cupom;
import com.google.common.base.Strings;

@Repository
public class CupomDAO extends GenericDAO<Cupom> implements ICupomDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cupom> filter(Filter<Cupom> filter) {

		boolean validFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM ").append(Cupom.class.getName()).append(" c ");

		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getCode() != null
				&& !Strings.isNullOrEmpty(filter.getEntity().getCode())) {
			jpql.append(" AND c.code = :code");
		}
		
		if (validFilter && filter.getEntity().getOwner() != null
				&& filter.getEntity().getOwner().getId() > 0) {
			jpql.append(" AND c.owner.id = :ownerId");
		}
		
		if (validFilter && filter.getEntity().getType() != null
				&& !Strings.isNullOrEmpty(filter.getEntity().getType().name())) {
			jpql.append(" AND c.type = :type");
		}
		
		if (validFilter && filter.getEntity().getActive() != null) {
			jpql.append(" AND c.active = :active");
		}
		
		TypedQuery<Cupom> query = em.createQuery(jpql.toString(), Cupom.class);

		if (validFilter && filter.getEntity().getCode() != null
				&& !Strings.isNullOrEmpty(filter.getEntity().getCode())) {
			query.setParameter("code", filter.getEntity().getCode());
		}
		
		if (validFilter && filter.getEntity().getOwner() != null
				&& filter.getEntity().getOwner().getId() > 0) {
			query.setParameter("ownerId", filter.getEntity().getOwner().getId());
		}
		
		if (validFilter && filter.getEntity().getType() != null
				&& !Strings.isNullOrEmpty(filter.getEntity().getType().name())) {
			query.setParameter("type", filter.getEntity().getType().name());
		}
		
		if (validFilter && filter.getEntity().getActive() != null) {
			query.setParameter("active", filter.getEntity().getActive());
		}
		
		return query.getResultList();
	}
}

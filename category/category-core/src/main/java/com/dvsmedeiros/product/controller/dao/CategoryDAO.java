package com.dvsmedeiros.product.controller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.category.domain.Category;

@Component
public class CategoryDAO extends GenericDAO<Category> implements ICategoryDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Category> filter(Filter<Category> filter) {
		
		boolean validFilter = filter != null && filter.getEntity() != null;
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM ").append(Category.class.getName()).append(" c ");
		
		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getDescription() != null) {
			jpql.append(" AND c.description like :description");
		}
		
		if (validFilter && filter.getEntity().getCode() != null) {
			jpql.append(" AND c.code = :code");
		}

		if (validFilter && filter.getEntity().getType() != null &&  filter.getEntity().getType().getCode() != null ) {
			jpql.append(" AND c.type.code = :type");
		}
		
		if (validFilter && filter.getEntity().getActive() != null) {
			jpql.append(" AND c.active = :active");
		}

		TypedQuery<Category> query = em.createQuery(jpql.toString(), Category.class);

		if (validFilter && filter.getEntity().getDescription() != null) {
			query.setParameter("description", "%" + filter.getEntity().getDescription() + "%");
		}
		
		if (validFilter && filter.getEntity().getCode() != null) {
			query.setParameter("code", filter.getEntity().getCode());
		}

		if (validFilter && filter.getEntity().getType() != null &&  filter.getEntity().getType().getCode() != null ) {
			query.setParameter("type", filter.getEntity().getType().getCode());
		}
		
		if (validFilter && filter.getEntity().getActive() != null) {
			query.setParameter("active", filter.getEntity().getActive());
		}
		
		return query.getResultList();
	}

}

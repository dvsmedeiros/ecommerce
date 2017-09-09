package com.dvsmedeiros.product.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.dao.IProductDAO;
import com.dvsmedeiros.product.domain.Product;

@Component
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Product> filter(Filter<Product> filter) {
		boolean validFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT p FROM ").append(Product.class.getName()).append(" p ");

		if (validFilter && filter.getEntity().getCategory().getId() > 0) {
			jpql.append(" JOIN FETCH p.category c ");
		}

		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getDescription() != null) {
			jpql.append(" AND p.description like :description");
		}

		if (validFilter && filter.getEntity().getActive() != null) {
			jpql.append(" AND p.active = :active");
		}

		if (validFilter && filter.getEntity().getCategory().getId() > 0) {
			jpql.append(" AND c.id = :categoryId");
		}

		TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);

		if (validFilter && filter.getEntity().getDescription() != null) {
			query.setParameter("description", "%" + filter.getEntity().getDescription() + "%");
		}

		if (validFilter && filter.getEntity().getActive() != null) {
			query.setParameter("active", filter.getEntity().getActive());
		}

		if (validFilter && filter.getEntity().getCategory().getId() > 0) {
			query.setParameter("categoryId", filter.getEntity().getCategory().getId());
		}

		return query.getResultList();
	} 

}

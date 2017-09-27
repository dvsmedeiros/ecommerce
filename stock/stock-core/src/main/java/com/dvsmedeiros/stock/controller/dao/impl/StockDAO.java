package com.dvsmedeiros.stock.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.domain.Price;
import com.dvsmedeiros.stock.controller.dao.IStockDAO;
import com.dvsmedeiros.stock.domain.Stock;

@Component
public class StockDAO extends GenericDAO<Stock> implements IStockDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Stock> filter(Filter<Stock> filter) {
		
		boolean validFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT s FROM ").append(Stock.class.getName()).append(" s ");

		if (validFilter && filter.getEntity().getProduct().getId() > 0) {
			jpql.append(" JOIN FETCH s.product p ");
		}

		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getProduct().getId() > 0) {
			jpql.append(" AND p.id = :productId");
		}

		TypedQuery<Stock> query = em.createQuery(jpql.toString(), Stock.class);

		if (validFilter && filter.getEntity().getProduct().getId() > 0) {
			query.setParameter("productId", filter.getEntity().getProduct().getId());
		}

		return query.getResultList();
	}

}

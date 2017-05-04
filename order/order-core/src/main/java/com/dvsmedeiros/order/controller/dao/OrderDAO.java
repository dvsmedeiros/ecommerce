package com.dvsmedeiros.order.controller.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.dvsmedeiros.bce.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.controller.dao.impl.IOrderDAO;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.product.domain.Product;

@Repository
@SuppressWarnings("unchecked")
public class OrderDAO extends GenericDAO<Order> implements IOrderDAO {

	@Override
	public List<Order> filter(Filter<Order> filter) {

		boolean validFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT o FROM ").append(Order.class.getName()).append(" o ");
		
		if (validFilter && filter.getEntity().getUser() != null) {
			jpql.append( " JOIN FETCH o.user u " );
		}
        
        jpql.append(" WHERE 1=1 ");
	
		if (validFilter && filter.getEntity().getUser() != null) {
			jpql.append(" AND u.id = :userId");
		}

		Query query = em.createQuery(jpql.toString());
		
		if (validFilter && filter.getEntity().getUser() != null) {
			query.setParameter("userId", filter.getEntity().getUser().getId());
		}

		return query.getResultList();
	}

}

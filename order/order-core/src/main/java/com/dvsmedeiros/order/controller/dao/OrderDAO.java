package com.dvsmedeiros.order.controller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.controller.dao.impl.IOrderDAO;
import com.dvsmedeiros.order.domain.Order;

@Repository
public class OrderDAO extends GenericDAO<Order> implements IOrderDAO{
	
	@PersistenceContext
	private EntityManager em;
	
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
		
		if (validFilter && filter.getEntity().getStatusOrder() != null && filter.getEntity().getStatusOrder().getId() > 0) {
			jpql.append(" AND o.statusOrder.id = :statusOrderId");
		}
		
		TypedQuery<Order> query = em.createQuery(jpql.toString(), Order.class);
		
		if (validFilter && filter.getEntity().getUser() != null) {
			query.setParameter("userId", filter.getEntity().getUser().getId());
		}
		
		if (validFilter && filter.getEntity().getStatusOrder() != null && filter.getEntity().getStatusOrder().getId() > 0) {
			query.setParameter("statusOrderId", filter.getEntity().getStatusOrder().getId());
		}
		return query.getResultList();
	}
}

package com.dvsmedeiros.commons.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.dao.ICupomDAO;
import com.dvsmedeiros.commons.controller.dao.INotificationDAO;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.Notification;
import com.google.common.base.Strings;

@Repository
public class NotificationDAO extends GenericDAO<Notification> implements INotificationDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Notification> filter(Filter<Notification> filter) {

		boolean validFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM ").append(Notification.class.getName()).append(" c ");

		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getCode() != null
				&& !Strings.isNullOrEmpty(filter.getEntity().getCode())) {
			jpql.append(" AND c.code = :code");
		}
		
		if (validFilter && filter.getEntity().getOwner() != null
				&& filter.getEntity().getOwner().getId() > 0) {
			jpql.append(" AND c.owner.id = :ownerId");
		}
		
		if (validFilter && filter.getEntity().isReaded()) {
			jpql.append(" AND c.readed = :readed");
		}
		
		TypedQuery<Notification> query = em.createQuery(jpql.toString(), Notification.class);

		if (validFilter && filter.getEntity().getCode() != null
				&& !Strings.isNullOrEmpty(filter.getEntity().getCode())) {
			query.setParameter("code", filter.getEntity().getCode());
		}
		
		if (validFilter && filter.getEntity().getOwner() != null
				&& filter.getEntity().getOwner().getId() > 0) {
			query.setParameter("ownerId", filter.getEntity().getOwner().getId());
		}
		
		if (validFilter && filter.getEntity().isReaded()) {
			query.setParameter("readed", filter.getEntity().isReaded());
		}
		
		return query.getResultList();
	}
}

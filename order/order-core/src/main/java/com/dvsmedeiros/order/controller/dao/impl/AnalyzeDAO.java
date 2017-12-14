package com.dvsmedeiros.order.controller.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.core.utils.BceUtils;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Analyze;
import com.dvsmedeiros.commons.domain.PercentSaleByCategoryGenderAgeBean;
import com.dvsmedeiros.commons.domain.QuantitySaleByCategoryPeriodBean;
import com.dvsmedeiros.order.controller.dao.IAnalyzeDAO;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.StatusOrder;

@Component
public class AnalyzeDAO extends GenericDAO<Analyze> implements IAnalyzeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PercentSaleByCategoryGenderAgeBean> percentSaleByCategoryGenderAge(Filter<Analyze> filter) {

		List<String> statusOrder = new ArrayList<>();
		statusOrder.add(StatusOrder.APPROVED);
		statusOrder.add(StatusOrder.SEPARATION);
		statusOrder.add(StatusOrder.TRANSPORTATION);
		statusOrder.add(StatusOrder.DELIVERY);
		statusOrder.add(StatusOrder.EXCHANGED);

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT new com.dvsmedeiros.commons.domain.PercentSaleByCategoryGenderAgeBean( avg(u.age), u.gender, c.description, sum(i.quantity) )");
		jpql.append(" FROM ").append(Order.class.getName()).append(" o ");
		jpql.append(" JOIN o.items i ");
		jpql.append(" JOIN i.product p ");
		jpql.append(" JOIN p.categories c ");
		jpql.append(" JOIN o.custumer u ");
		jpql.append(" WHERE 1=1 ");
		if(filter != null && filter.getEntity() != null && filter.getEntity().getCategories() != null && !filter.getEntity().getCategories().isEmpty()) {
			jpql.append(" AND c in ( :categories ) ");
		} else {
			jpql.append(" AND c in ( select ca from Category ca JOIN ca.type ct WHERE ct.code = 'PROD' ) ");
		}
		jpql.append(" AND o.statusOrder in ( select s from StatusOrder s WHERE s.code in( :status ) ) ");
		jpql.append(" AND o.insertionDate BETWEEN :start AND :end ");
		jpql.append(" GROUP BY c.description, u.bornDate, u.gender ");

		Query query = em.createQuery(jpql.toString());
		
		query.setParameter("status", statusOrder);
		query.setParameter("start", BceUtils.startDay(filter.getEntity().getPeriod().getStart()));
		query.setParameter("end", BceUtils.endDay(filter.getEntity().getPeriod().getEnd()));
		if(filter != null && filter.getEntity() != null && filter.getEntity().getCategories() != null && !filter.getEntity().getCategories().isEmpty()) {
			query.setParameter("categories", filter.getEntity().getCategories());
		}

		return (List<PercentSaleByCategoryGenderAgeBean>) query.getResultList();
	}

	@Override
	public List<QuantitySaleByCategoryPeriodBean> quantitySaleByCategoryPeriod(Filter<Analyze> aEntity) {
		
		List<String> statusOrder = new ArrayList<>();
		statusOrder.add(StatusOrder.APPROVED);
		statusOrder.add(StatusOrder.SEPARATION);
		statusOrder.add(StatusOrder.TRANSPORTATION);
		statusOrder.add(StatusOrder.DELIVERY);
		statusOrder.add(StatusOrder.EXCHANGED);

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT new com.dvsmedeiros.commons.domain.QuantitySaleByCategoryPeriodBean( o.month, c.description, sum(i.quantity) )");
		jpql.append(" FROM ").append(Order.class.getName()).append(" o ");
		jpql.append(" JOIN o.items i ");
		jpql.append(" JOIN i.product p ");
		jpql.append(" JOIN p.categories c ");
		jpql.append(" WHERE 1=1 ");
		jpql.append(" AND o.statusOrder in (select s from StatusOrder s WHERE s.code in( :status ) ) ");
		jpql.append(" AND o.insertionDate BETWEEN :start AND :end ");
		jpql.append(" GROUP BY o.month, o.year, c.description");

		Query query = em.createQuery(jpql.toString());

		query.setParameter("status", statusOrder);
		query.setParameter("start", BceUtils.startDay(Calendar.getInstance()));
		query.setParameter("end", BceUtils.endDay(Calendar.getInstance()));

		return (List<QuantitySaleByCategoryPeriodBean>) query.getResultList();
		
	}
}

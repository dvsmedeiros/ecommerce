package com.dvsmedeiros.commons.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.core.utils.BceUtils;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.dao.IUserDAO;
import com.dvsmedeiros.commons.domain.User;
import com.google.common.base.Strings;

@Component
public class UserDAO extends GenericDAO<User> implements IUserDAO, UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> filter(Filter<User> filter) {
	
		boolean validFilter = filter != null && filter.getEntity() != null;
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT u FROM ").append(User.class.getName()).append(" u ");
		
		jpql.append(" WHERE 1=1 ");

		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getFirstName())) {
			jpql.append(" AND lower(u.firstName) like :firstname");
		}
		
		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getLastName())) {
			jpql.append(" AND lower(u.lastName) like :lastname");
		}
		
		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getEmail())) {
			jpql.append(" AND lower(u.email) = :email");
		}
		
		if (validFilter && filter.getEntity().getGender() != null) {
			jpql.append(" AND u.gender = :gender");
		}
		
		if (validFilter && filter.getEntity().getCpf() != null && !Strings.isNullOrEmpty(filter.getEntity().getCpf().getNumber())) {
			jpql.append(" AND u.cpf.number = :cpf");
		}
		
		if (validFilter && filter.getEntity().getBornDate() != null) {
			jpql.append(" AND u.bornDate BETWEEN :start AND :end");
		}
		
		if (validFilter && filter.getEntity().getCode() != null) {
			jpql.append(" AND u.code = :code");
		}
		
		if (validFilter && filter.getEntity().getActive() != null) {
			jpql.append(" AND u.active = :active");
		}

		TypedQuery<User> query = em.createQuery(jpql.toString(), User.class);
		
		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getFirstName())) {
			query.setParameter("firstname", "%".concat(filter.getEntity().getFirstName().toLowerCase()).concat("%"));
		}
		
		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getLastName())) {
			query.setParameter("lastname", "%".concat(filter.getEntity().getLastName().toLowerCase()).concat("%"));
		}
		
		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getEmail())) {
			query.setParameter("email", filter.getEntity().getEmail().toLowerCase());
		}
		
		if (validFilter && filter.getEntity().getGender() != null) {
			query.setParameter("gender", filter.getEntity().getGender());
		}
		
		if (validFilter && filter.getEntity().getCpf() != null && !Strings.isNullOrEmpty(filter.getEntity().getCpf().getNumber())) {
			query.setParameter("cpf", filter.getEntity().getCpf().getNumber());
		}
		
		if (validFilter && filter.getEntity().getBornDate() != null) {
			query.setParameter("start", BceUtils.startDay(filter.getEntity().getBornDate()), TemporalType.TIMESTAMP);
			query.setParameter("end", BceUtils.endDay(filter.getEntity().getBornDate()), TemporalType.TIMESTAMP);
		}
		
		if (validFilter && filter.getEntity().getCode() != null) {
			query.setParameter("code", filter.getEntity().getCode());
		}
		
		if (validFilter && filter.getEntity().getActive() != null) {
			query.setParameter("active", filter.getEntity().getActive());
		}
		
		return query.getResultList();
	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		
		try {
			
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT u FROM ").append(User.class.getName()).append(" u ");
			
			jpql.append(" WHERE 1=1 ");
			
			if (!Strings.isNullOrEmpty(username)) {
				jpql.append(" AND lower(u.email) = :email");
			}
			
			jpql.append(" AND u.active = true");
			
			TypedQuery<User> query = em.createQuery(jpql.toString(), User.class);
			
			if (!Strings.isNullOrEmpty(username)) {
				query.setParameter("email", username);
			}
			
			return query.getSingleResult();
			
		} catch (Exception e) {
			
			return null;
		}
	}

}

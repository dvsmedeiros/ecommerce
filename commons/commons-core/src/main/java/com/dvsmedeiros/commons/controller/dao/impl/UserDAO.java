package com.dvsmedeiros.commons.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.dao.IUserDAO;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.Individual;
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

		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getEmail())) {
			jpql.append(" AND lower(u.email) = :email");
		}

		if (validFilter && filter.getEntity().getCode() != null) {
			jpql.append(" AND u.code = :code");
		}

		if (validFilter && filter.getEntity().getActive() != null) {
			jpql.append(" AND u.active = :active");
		}

		TypedQuery<User> query = em.createQuery(jpql.toString(), User.class);

		if (validFilter && !Strings.isNullOrEmpty(filter.getEntity().getEmail())) {
			query.setParameter("email", filter.getEntity().getEmail().toLowerCase());
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
	public UserDetails loadUserByUsername(String username) {

		try {

			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT u FROM ").append(User.class.getName()).append(" u ");

			jpql.append(" WHERE 1=1 ");

			if (!Strings.isNullOrEmpty(username)) {
				jpql.append(" AND lower(u.email) = :email");
			}

			jpql.append(" AND u.active = true");

			Query query = em.createQuery(jpql.toString());

			if (!Strings.isNullOrEmpty(username)) {
				query.setParameter("email", username);
			}

			return (UserDetails) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Individual findIndividualByUser(long id) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT i FROM ").append(Individual.class.getName()).append(" i ");

		jpql.append(" WHERE 1=1 ");

		if (id > 0) {
			jpql.append(" AND i.user.id = :id");
		}

		TypedQuery<Individual> query = em.createQuery(jpql.toString(), Individual.class);

		if (id > 0) {
			query.setParameter("id", id);
		}

		return query.getSingleResult();
	}

	public Client findClientByUser(long id) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM ").append(Client.class.getName()).append(" c ");

		jpql.append(" WHERE 1=1 ");

		if (id > 0) {
			jpql.append(" AND c.user.id = :id");
		}

		TypedQuery<Client> query = em.createQuery(jpql.toString(), Client.class);

		if (id > 0) {
			query.setParameter("id", id);
		}

		return query.getSingleResult();

	}
}

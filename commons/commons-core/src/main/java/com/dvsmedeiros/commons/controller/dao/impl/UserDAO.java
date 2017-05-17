package com.dvsmedeiros.commons.controller.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.dao.impl.GenericDAO;
import com.dvsmedeiros.commons.controller.dao.IUserDAO;
import com.dvsmedeiros.commons.domain.User;

@Component
@SuppressWarnings("unchecked")
public class UserDAO extends GenericDAO<User> implements IUserDAO {

	@Override
	public User loadUserByUsername(String login) {

		boolean validLogin = login != null && !login.isEmpty();

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT u FROM ").append(User.class.getName()).append(" u ");

		jpql.append(" WHERE 1=1 ");

		if (validLogin) {
			jpql.append(" AND u.email = :email");
		}

		Query query = em.createQuery(jpql.toString());

		if (validLogin) {
			query.setParameter("email", login);
		}

		List<User> resultList = query.getResultList();
		
		if(!resultList.isEmpty()){
			return resultList.get(0);
		}
		
		return null;
	}

}

package com.dvsmedeiros.supplier.controller.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.dao.impl.GenericDAO;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.supplier.controller.dao.IUserDAO;

@Component
public class UserDAO extends GenericDAO<User> implements IUserDAO, UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) {
		
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

		List<User> users = query.getResultList();
		
		if (users.isEmpty()) {
            throw new UsernameNotFoundException("User: " + login + " not found");
        }

        return users.get(0);
	}

}

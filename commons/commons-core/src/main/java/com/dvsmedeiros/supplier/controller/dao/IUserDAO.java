package com.dvsmedeiros.supplier.controller.dao;

import org.springframework.security.core.userdetails.UserDetails;

import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.commons.domain.User;

public interface IUserDAO extends IDAO<User> {

	public UserDetails loadUserByUsername(String login);
}

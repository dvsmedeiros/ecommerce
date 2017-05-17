package com.dvsmedeiros.commons.controller.dao;


import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.commons.domain.User;

public interface IUserDAO extends IDAO<User> {

	public User loadUserByUsername(String login);
}

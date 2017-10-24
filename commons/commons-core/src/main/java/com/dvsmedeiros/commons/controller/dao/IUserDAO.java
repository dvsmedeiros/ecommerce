package com.dvsmedeiros.commons.controller.dao;

import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.User;

public interface IUserDAO extends IDAO<User>{
	
	List<User> filter(Filter<User> aEntity);
	
}

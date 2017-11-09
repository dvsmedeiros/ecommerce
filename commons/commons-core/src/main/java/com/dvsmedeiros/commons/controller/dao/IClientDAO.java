package com.dvsmedeiros.commons.controller.dao;

import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Client;

public interface IClientDAO extends IDAO<Client>{
	
	List<Client> filter(Filter<Client> aEntity);
	Client findClientByUser(long id);
	
}

package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.Role;

@Component
public class ClientUserValidator extends ApplicationEntity implements IStrategy<Client> {

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Role> dao;
	
	@Override
	public void process(Client aEntity, INavigationCase<Client> aCase) {
		
		Role userRole = (Role) dao.find(Role.class, Role.USER_ROLE);
		if(userRole != null) {
			aEntity.getUser().addRole(userRole);
			return;
		}
		aCase.suspendExecution("Permissão inexistente ou inválida");
		getLogger(this.getClass()).debug("ROLE: " + Role.USER_ROLE + " não foi encontrada!");
	}

}

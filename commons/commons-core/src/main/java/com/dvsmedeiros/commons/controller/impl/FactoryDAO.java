package com.dvsmedeiros.commons.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.commons.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.DomainEntity;

@Repository
@SuppressWarnings("rawtypes")
public class FactoryDAO extends ApplicationEntity {
	
	@Autowired
	private Map<String, IDAO<?>> daos;
	
	public IDAO create(String className){
		
		IDAO dao = daos.get(className);
		if(dao == null){
			return daos.get(DomainEntity.class.getName());
		}
		return dao;
	}
}

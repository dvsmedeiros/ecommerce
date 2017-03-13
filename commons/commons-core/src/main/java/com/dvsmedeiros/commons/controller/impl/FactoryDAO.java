package com.dvsmedeiros.commons.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.commons.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

@Repository
@SuppressWarnings("rawtypes")
public class FactoryDAO extends ApplicationEntity {

	@Autowired
	private Map<String, IDAO<?>> daos;

	public IDAO create(Class<?> clazz) {

		IDAO dao = daos.get(clazz.getName());
		if (dao == null) {
			
			if (DomainSpecificEntity.class.isAssignableFrom(clazz)) {
				return daos.get(DomainSpecificEntity.class.getName());
				
			} else if (DomainEntity.class.isAssignableFrom(clazz)) {
				return daos.get(DomainEntity.class.getName());
			}
		}
		return dao;
	}
}

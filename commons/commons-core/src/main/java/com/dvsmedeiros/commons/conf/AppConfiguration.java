package com.dvsmedeiros.commons.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.dao.impl.GenericDAO;
import com.dvsmedeiros.commons.dao.impl.GenericSpecificDAO;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

@Configuration
public class AppConfiguration {
	
	@Bean(name="com.dvsmedeiros.commons.domain.DomainEntity")
	public GenericDAO<DomainEntity> getGenericDAO(){
		return new GenericDAO<>();
	}
	
	@Bean(name="com.dvsmedeiros.commons.domain.DomainSpecificEntity")
	public GenericSpecificDAO<DomainSpecificEntity> getGenericSpecificDAO(){
		return new GenericSpecificDAO<>();
	}
	
}

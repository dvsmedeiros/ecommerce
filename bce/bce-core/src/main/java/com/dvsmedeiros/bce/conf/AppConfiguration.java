package com.dvsmedeiros.bce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.dao.impl.GenericSpecificDAO;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Configuration
public class AppConfiguration {
	
	@Bean(name="com.dvsmedeiros.bce.domain.DomainEntity")
	public GenericDAO<DomainEntity> getGenericDAO(){
		return new GenericDAO<>();
	}
	
	@Bean(name="com.dvsmedeiros.bce.domain.DomainSpecificEntity")
	public GenericSpecificDAO<DomainSpecificEntity> getGenericSpecificDAO(){
		return new GenericSpecificDAO<>();
	}
	
}

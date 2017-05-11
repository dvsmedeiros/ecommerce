package com.dvsmedeiros.supplier.controller.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.commons.domain.User;

@Configuration
public class UserNavigation {
	
	@Bean(name="SINGUP")
	public EntityRuleDefinition<User> getSaveProductNavigation(){
		
		EntityRuleDefinition<User> activities = new EntityRuleDefinition<>();
		return activities;
	}
}

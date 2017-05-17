package com.dvsmedeiros.commons.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.commons.controller.business.impl.AuthenticateUser;
import com.dvsmedeiros.commons.controller.business.impl.FindUserByEmail;
import com.dvsmedeiros.commons.controller.business.impl.PasswordValidator;
import com.dvsmedeiros.commons.domain.User;

@Configuration
public class AuthenticateNavigation {

	@Autowired
	private FindUserByEmail findUserByEmail;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private AuthenticateUser authenticateUser;
	
	@Bean(name = "DO_LOGIN")
	public EntityRuleDefinition<User> doLogin() {

		EntityRuleDefinition<User> activities = new EntityRuleDefinition<>();
		activities.addActivity(findUserByEmail);
		activities.addActivity(passwordValidator);
		activities.addActivity(authenticateUser);
		
		return activities;
	}
}

package com.dvsmedeiros.commons.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.CodeGenerator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
//import com.dvsmedeiros.bce.domain.Audit;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.business.impl.ActualPasswordValidator;
import com.dvsmedeiros.commons.controller.business.impl.EmailValidator;
import com.dvsmedeiros.commons.controller.business.impl.FindFilterUser;
import com.dvsmedeiros.commons.controller.business.impl.PasswordValidator;
import com.dvsmedeiros.commons.controller.business.impl.SendAuditToProcess;
import com.dvsmedeiros.commons.domain.User;

@Configuration
public class UserNavigation {
	
	@Autowired private CodeGenerator codeGenarator;
	@Autowired private FindFilterUser findFilterUser;
	@Autowired private EmailValidator emailValidator;
	@Autowired private PasswordValidator passwordValidator;
	@Autowired private ActualPasswordValidator actualPasswordValidator;
	@Autowired private SendAuditToProcess sendAuditToProcess;
	
	//@Bean(name = "LOG_AUDIT")
	//public Navigation<Audit> audit() {
	//
	//	return new NavigationBuilder<Audit>()
	//			.next(sendAuditToProcess)
	//			.build();
	//}
	
	@Bean("USER_VALIDATOR")
	public Navigation<User> userValidator(){
		return new NavigationBuilder<User>()
				.next(emailValidator)
				.next(passwordValidator)
				.next(codeGenarator)
				.build();
	}
	
	@Bean("CHANGE_PASSWORD")
	public Navigation<User> changePassword(){
		return new NavigationBuilder<User>()
				.next(actualPasswordValidator)
				.next(passwordValidator)
				.build();
	}
	
	@Bean("FILTER_USER")
	public Navigation<Filter<User>> filterReason(){
		return new NavigationBuilder<Filter<User>>()
				.next(findFilterUser)				
				.build();
	}
}

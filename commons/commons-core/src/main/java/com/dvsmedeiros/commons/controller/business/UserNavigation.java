package com.dvsmedeiros.commons.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.CodeGenerator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.business.impl.BornDateValidator;
import com.dvsmedeiros.commons.controller.business.impl.CPFValidator;
import com.dvsmedeiros.commons.controller.business.impl.EmailValidator;
import com.dvsmedeiros.commons.controller.business.impl.FillUserAddress;
import com.dvsmedeiros.commons.controller.business.impl.FindFilterUser;
import com.dvsmedeiros.commons.controller.business.impl.FirstNameValidator;
import com.dvsmedeiros.commons.controller.business.impl.GenderValidator;
import com.dvsmedeiros.commons.controller.business.impl.LastNameValidator;
import com.dvsmedeiros.commons.controller.business.impl.PasswordValidator;
import com.dvsmedeiros.commons.controller.business.impl.PhoneValidator;
import com.dvsmedeiros.commons.controller.business.impl.UserAddressValidator;
import com.dvsmedeiros.commons.domain.User;

@Configuration
public class UserNavigation {
	
	@Autowired private CodeGenerator codeGenarator;
	@Autowired private FindFilterUser findFilterUser;
	@Autowired private BornDateValidator bornDateValidator;
	@Autowired private CPFValidator cpfValidator;
	@Autowired private EmailValidator emailValidator;
	@Autowired private FirstNameValidator firstNameValidator;
	@Autowired private LastNameValidator lastNameValidator;
	@Autowired private PasswordValidator passwordValidator;
	@Autowired private PhoneValidator phoneValidator;
	@Autowired private GenderValidator genderValidator;
	@Autowired private UserAddressValidator userAddressValidator;
	@Autowired private FillUserAddress fillUserAddress;
	
	
	@Bean("SAVE_USER")
	public Navigation<User> saveUser(){
		return new NavigationBuilder<User>()
				.next(firstNameValidator)
				.next(lastNameValidator)
				.next(emailValidator)
				.next(passwordValidator)
				.next(cpfValidator)
				.next(genderValidator)
				.next(bornDateValidator)
				.next(phoneValidator)
				.next(userAddressValidator)
				.next(fillUserAddress)
				.next(codeGenarator)				
				.build();
	}
	
	@Bean("UPDATE_USER")
	public Navigation<User> updateUser(){
		return new NavigationBuilder<User>()
				.next(firstNameValidator)
				.next(lastNameValidator)				
				.next(passwordValidator)
				.next(cpfValidator)
				.next(genderValidator)
				.next(bornDateValidator)
				.next(phoneValidator)												
				.build();
	}
	
	@Bean("FILTER_USER")
	public Navigation<Filter<User>> filterReason(){
		return new NavigationBuilder<Filter<User>>()
				.next(findFilterUser)				
				.build();
	}
}

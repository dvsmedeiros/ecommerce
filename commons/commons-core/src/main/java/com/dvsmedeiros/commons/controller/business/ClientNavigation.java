package com.dvsmedeiros.commons.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.CodeGenerator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.business.impl.AddUserRole;
import com.dvsmedeiros.commons.controller.business.impl.BornDateValidator;
import com.dvsmedeiros.commons.controller.business.impl.CPFValidator;
import com.dvsmedeiros.commons.controller.business.impl.ClientUserValidator;
import com.dvsmedeiros.commons.controller.business.impl.FillUserAddress;
import com.dvsmedeiros.commons.controller.business.impl.FindFilterClient;
import com.dvsmedeiros.commons.controller.business.impl.FirstNameValidator;
import com.dvsmedeiros.commons.controller.business.impl.GenderValidator;
import com.dvsmedeiros.commons.controller.business.impl.LastNameValidator;
import com.dvsmedeiros.commons.controller.business.impl.PhoneValidator;
import com.dvsmedeiros.commons.controller.business.impl.UserAddressValidator;
import com.dvsmedeiros.commons.domain.Client;

@Configuration
public class ClientNavigation {
	
	@Autowired private CodeGenerator codeGenarator;
	@Autowired private BornDateValidator bornDateValidator;
	@Autowired private CPFValidator cpfValidator;
	@Autowired private FirstNameValidator firstNameValidator;
	@Autowired private LastNameValidator lastNameValidator;
	@Autowired private PhoneValidator phoneValidator;
	@Autowired private GenderValidator genderValidator;
	@Autowired private UserAddressValidator userAddressValidator;
	@Autowired private FillUserAddress fillUserAddress;
	@Autowired private ClientUserValidator clientUserValidator;
	@Autowired private FindFilterClient findFilterClient;
	@Autowired private AddUserRole addUserRole;
	
	
	@Bean("SAVE_CLIENT")
	public Navigation<Client> saveUser(){
		return new NavigationBuilder<Client>()
				.next(firstNameValidator)
				.next(lastNameValidator)
				.next(clientUserValidator)
				.next(cpfValidator)
				.next(genderValidator)
				.next(bornDateValidator)
				.next(phoneValidator)
				.next(userAddressValidator)
				.next(fillUserAddress)
				.next(codeGenarator)
				.next(addUserRole)
				.build();
	}
	
	@Bean("UPDATE_CLIENT")
	public Navigation<Client> updateUser(){
		return new NavigationBuilder<Client>()
				.next(firstNameValidator)
				.next(lastNameValidator)								
				.next(cpfValidator)
				.next(genderValidator)
				.next(bornDateValidator)
				.next(phoneValidator)												
				.build();
	}
		
	@Bean("FILTER_CLIENT")
	public Navigation<Filter<Client>> filterReason(){
		return new NavigationBuilder<Filter<Client>>()
				.next(findFilterClient)
				.build();
	}
}

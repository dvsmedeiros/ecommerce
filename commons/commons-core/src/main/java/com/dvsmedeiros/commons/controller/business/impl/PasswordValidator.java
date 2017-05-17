package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;

@Component
public class PasswordValidator implements IStrategy<User> {
	
	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		User user = aCase.getContext().getAttribute("user");

		if (user == null) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("Usuário: " + aEntity.getEmail() + " não encontrado!");
		}
		
		if(! user.getPassword().equals(aEntity.getPassword())){
			aCase.suspendExecution();
			aCase.getResult().setMessage("Senha inválida!");
		}
		
	}

}

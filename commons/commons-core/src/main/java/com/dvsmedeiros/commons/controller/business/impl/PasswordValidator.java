package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;
import com.google.common.base.Strings;

@Component
public class PasswordValidator implements IStrategy<User> {
	private static final String REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]).{8,20})";
	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getPassword()) && !Strings.isNullOrEmpty(aEntity.getConfirmPassword())) {
			if(!aEntity.getPassword().equals(aEntity.getConfirmPassword())) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("Senhas não são compativeis");
				return;
			}
			if(!aEntity.getPassword().matches(REGEX)) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("A senha deve ter no mínimo 8 caracteres sendo [a-z], [A-Z], [0-9] e [!@#$%&]");
				return;
			}
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Senha inexistente ou inválida");

	}

}

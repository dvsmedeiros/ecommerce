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

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getNewPassword()) && !Strings.isNullOrEmpty(aEntity.getConfirmPassword())) {
			if(!aEntity.getNewPassword().equals(aEntity.getConfirmPassword())) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("Senhas não são compativeis");
				return;
			}
			if(!aEntity.getNewPassword().matches(REGEX)) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("A senha deve ter no mínimo 8 caracteres sendo [a-z], [A-Z], [0-9] e [!@#$%&]");
				return;
			}
			aEntity.setPassword(aEntity.getNewPassword());
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Senha inexistente ou inválida");

	}

}

package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Phone;
import com.dvsmedeiros.commons.domain.User;
import com.google.common.base.Strings;

@Component
public class PhoneValidator implements IStrategy<User> {

	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		if (aEntity != null && aEntity.getPhones() != null && !aEntity.getPhones().isEmpty()) {

			for (Phone phone : aEntity.getPhones()) {
				if (Strings.isNullOrEmpty(phone.getPhone())) {
					aCase.suspendExecution();
					aCase.getResult().setMessage("Telefone inexistente ou inválido");
					return;
				}
			}
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Telefone inexistente ou inválido");

	}

}

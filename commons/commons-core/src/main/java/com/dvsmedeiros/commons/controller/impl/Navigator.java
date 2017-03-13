package com.dvsmedeiros.commons.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.DomainEntity;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Navigator<E extends DomainEntity> implements INavigator {

	@Autowired
	private Map<String, EntityRuleDefinition<E>> listNavigations = new HashMap<String, EntityRuleDefinition<E>>();

	@Override
	public void run(DomainEntity aEntity, INavigationCase aCase) {

		navigate(aEntity, aCase);
	}

	private void navigate(DomainEntity aEntity, INavigationCase aCase) {

		if (aEntity != null) {

			EntityRuleDefinition<E> entityRuleDefinition = listNavigations.get(aCase.getName());

			if (entityRuleDefinition != null && !aCase.isSuspendExecution()) {

				List<IStrategy<? super E>> activities = entityRuleDefinition.getActivities();

				for (IStrategy strategy : activities) {

					strategy.process(aEntity, aCase);
					if(aCase.isSuspendExecution()) break;
				}

			} else if (!aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {

				aCase.suspendExecution();
				aCase.getResult().setMessage(aCase.getName() + " - Não foi encontrada!");
			}
		}
	}
}

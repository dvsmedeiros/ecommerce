package com.dvsmedeiros.bce.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.IEntity;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Navigator<E extends IEntity> implements INavigator {

	@Autowired
	private Map<String, EntityRuleDefinition<E>> listNavigations = new HashMap<String, EntityRuleDefinition<E>>();

	@Override
	public void run(IEntity aEntity, INavigationCase aCase) {

		navigate(aEntity, aCase);
	}

	private void navigate(IEntity aEntity, INavigationCase aCase) {

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
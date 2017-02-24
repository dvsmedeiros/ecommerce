package com.dvsmedeiros.commons.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.DomainEntity;

@Component
public class EntityRuleDefinition<E extends DomainEntity> extends ApplicationEntity {

	@Autowired
	private List<IStrategy<E>> activities;

	public void addActivity(IStrategy<E> activity) {
		this.activities.add(activity);
	}

	public List<IStrategy<E>> getActivities() {
		return activities;
	}

}

package com.dvsmedeiros.commons.controller.impl;

import java.util.ArrayList;
import java.util.List;

import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.IEntity;

public class EntityRuleDefinition<E extends IEntity> extends ApplicationEntity {
	
	private List<IStrategy<? super E>> activities;
	
	public EntityRuleDefinition() {
		this.activities = new ArrayList<>();
	}
	
	public void addActivity(IStrategy<? super E> activity) {
		this.activities.add(activity);
	}

	public List<IStrategy<? super E>> getActivities() {
		return activities;
	}

}

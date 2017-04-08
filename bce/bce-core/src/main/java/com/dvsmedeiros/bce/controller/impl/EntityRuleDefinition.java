package com.dvsmedeiros.bce.controller.impl;

import java.util.ArrayList;
import java.util.List;

import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.IEntity;

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

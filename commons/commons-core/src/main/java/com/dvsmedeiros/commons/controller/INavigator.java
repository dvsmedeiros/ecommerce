package com.dvsmedeiros.commons.controller;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.IEntity;

@SuppressWarnings("rawtypes")
public interface INavigator extends IEntity{

	public void run(DomainEntity aEntity, INavigationCase aCase);

}

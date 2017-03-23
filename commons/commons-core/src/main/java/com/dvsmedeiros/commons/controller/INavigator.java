package com.dvsmedeiros.commons.controller;

import com.dvsmedeiros.commons.domain.IEntity;

@SuppressWarnings("rawtypes")
public interface INavigator extends IEntity{

	public void run(IEntity aEntity, INavigationCase aCase);

}

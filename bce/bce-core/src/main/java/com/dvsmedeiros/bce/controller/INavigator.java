package com.dvsmedeiros.bce.controller;

import com.dvsmedeiros.bce.domain.IEntity;

@SuppressWarnings("rawtypes")
public interface INavigator extends IEntity{

	public void run(IEntity aEntity, INavigationCase aCase);

}

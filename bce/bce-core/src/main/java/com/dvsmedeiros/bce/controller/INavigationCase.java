package com.dvsmedeiros.bce.controller;

import com.dvsmedeiros.bce.domain.IEntity;
import com.dvsmedeiros.bce.domain.Result;

public interface INavigationCase<E extends IEntity> extends IEntity{

	public static final String DEFAULT_CONTEXT_NAME = "DEFAULT_CONTEXT";

	public String getName();

	public Result<E> getResult();
	
	public void suspendExecution();
	
	public Boolean isSuspendExecution();
	
}

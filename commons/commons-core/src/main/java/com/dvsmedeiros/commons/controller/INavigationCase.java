package com.dvsmedeiros.commons.controller;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.IEntity;
import com.dvsmedeiros.commons.domain.Result;

public interface INavigationCase<E extends DomainEntity> extends IEntity{

	public static final String DEFAULT_CONTEXT_NAME = "DEFAULT_CONTEXT";

	public String getName();

	public Result<E> getResult();
	
	public void suspendExecution();
	
	public Boolean isSuspendExecution();
	
}

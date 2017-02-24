package com.dvsmedeiros.commons.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.Result;

@Component
public class BusinessCase<E extends DomainEntity> implements INavigationCase<E> {

	@Autowired
	private Result<E> result;
	private String name;
	private Boolean suspend = false;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Result<E> getResult() {
		return this.result;
	}

	@Override
	public void suspendExecution() {
		this.suspend = true;
	}

	@Override
	public Boolean isSuspendExecution() {
		return this.suspend;
	}

	public void setResult(Result<E> result) {
		this.result = result;
	}

	public void setName(String name) {
		this.name = name;
	}

}

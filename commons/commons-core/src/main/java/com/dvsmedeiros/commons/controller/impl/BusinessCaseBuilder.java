package com.dvsmedeiros.commons.controller.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.DomainEntity;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BusinessCaseBuilder<E extends DomainEntity> {

	protected BusinessCase aCase;
	
	public BusinessCaseBuilder() {
		this.aCase = new BusinessCase<>();
	}
	
	public BusinessCaseBuilder<E> withName(String aBusinessCaseName) {
		aCase.setName(aBusinessCaseName);
		return this;
	}
	
	public BusinessCaseBuilder<E> forEntity(Class<? extends E> entity) {
		try {
			aCase.setEntity(entity.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public BusinessCase<E> build() {

		if (aCase.getName() == null || aCase.getName().isEmpty()) {
			aCase.setName(BusinessCase.DEFAULT_CONTEXT_NAME);
		}

		return aCase;
	}

}

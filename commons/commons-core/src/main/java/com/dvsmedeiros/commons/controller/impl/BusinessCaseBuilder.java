package com.dvsmedeiros.commons.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.DomainEntity;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class BusinessCaseBuilder<E extends DomainEntity> {

	@Autowired
	protected BusinessCase aCase;

	public BusinessCaseBuilder<E> withName(String aBusinessCaseName) {
		aCase.setName(aBusinessCaseName);
		return this;
	}

	public BusinessCase<E> build() {

		if (aCase.getName() == null || aCase.getName().isEmpty()) {
			aCase.setName(BusinessCase.DEFAULT_CONTEXT_NAME);
		}

		return aCase;
	}

}

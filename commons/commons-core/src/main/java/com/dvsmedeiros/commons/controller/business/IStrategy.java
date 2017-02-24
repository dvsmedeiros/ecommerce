package com.dvsmedeiros.commons.controller.business;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.IEntity;

public interface IStrategy<T extends DomainEntity> extends IEntity {

	public void process(T aEntity, INavigationCase<T> aCase);

}

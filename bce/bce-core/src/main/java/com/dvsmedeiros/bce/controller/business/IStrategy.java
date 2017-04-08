package com.dvsmedeiros.bce.controller.business;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.IEntity;

public interface IStrategy<T extends IEntity> extends IEntity {

	public void process(T aEntity, INavigationCase<T> aCase);

}

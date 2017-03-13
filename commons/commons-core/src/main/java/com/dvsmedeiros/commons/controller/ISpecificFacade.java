package com.dvsmedeiros.commons.controller;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.Result;

public interface ISpecificFacade<T extends DomainEntity> extends IFacade<T> {
	
//	public Result<T> find(String code, Class<? extends T> clazz, INavigationCase<T> aCase);
//	
//	public Result<T> delete(String code, Class<? extends T> clazz, INavigationCase<T> aCase);
//	
//	public Result<T> findAll(boolean active, Class<? extends T> clazz, INavigationCase<T> aCase);
	
}

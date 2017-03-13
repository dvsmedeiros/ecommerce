package com.dvsmedeiros.commons.controller;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;
import com.dvsmedeiros.commons.domain.Filter;
import com.dvsmedeiros.commons.domain.IEntity;
import com.dvsmedeiros.commons.domain.Result;

public interface IFacade<T extends DomainEntity> extends IEntity {

	public Result<T> save(T aEntity, INavigationCase<T> aCase);

	public Result<T> update(T aEntity, INavigationCase<T> aCase);

	public Result<T> delete(T aEntity, INavigationCase<T> aCase);

	public Result<T> findAll(Class<? extends DomainEntity> clazz, INavigationCase<T> aCase);

	public Result<T> find(Long id, Class<? extends DomainEntity> clazz, INavigationCase<T> aCase);
	
	public Result<T> find(Filter<T> aFilter, INavigationCase<T> aCase);
	
	public Result<T> find(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase);
	
	public Result<T> delete(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase);
	
	public Result<T> findAll(boolean active, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase);

}

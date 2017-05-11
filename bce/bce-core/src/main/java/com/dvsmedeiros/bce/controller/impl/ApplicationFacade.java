package com.dvsmedeiros.bce.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;

@Component
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ApplicationFacade<T extends DomainEntity> implements IFacade<T> {

	@Autowired
	private Navigator<T> navigator;

	@Autowired
	private FactoryDAO factoryDAO;

	@Override
	public Result save(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
			IDAO dao = factoryDAO.create(aEntity.getClass());
			dao.save(aEntity);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();

	}

	@Override
	public Result update(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
			IDAO dao = factoryDAO.create(aEntity.getClass());
			dao.update(aEntity);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result delete(T aEntity, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			IDAO dao = factoryDAO.create(aEntity.getClass());
			dao.delete(aEntity);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result findAll(Class<? extends DomainEntity> clazz, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			IDAO dao = factoryDAO.create(clazz);
			aCase.getResult().addEntities(dao.findAll(clazz));
		}
		return aCase.getResult();
	}

	@Override
	public Result find(Long id, Class<? extends DomainEntity> clazz, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			IDAO dao = factoryDAO.create(clazz);
			T aEntity = (T) dao.find(id, clazz);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result find(Filter<T> aFilter, INavigationCase<T> aCase) {

		navigator.run(aFilter, aCase);
		return aCase.getResult();

	}

	@Override
	public Result find(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			IDAO dao = factoryDAO.create(clazz);
			T aEntity = (T) dao.find(code, clazz);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result delete(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase) {
		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			IDAO dao = factoryDAO.create(clazz);
			dao.delete(code, clazz);
		}
		return aCase.getResult();
	}

	@Override
	public Result findAll(boolean active, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase) {
		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			IDAO dao = factoryDAO.create(clazz);
			List entityList = dao.findAll(active, clazz);
			aCase.getResult().addEntities(entityList);
		}
		return aCase.getResult();
	}

}

package com.dvsmedeiros.commons.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.ISpecificFacade;
import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.Filter;
import com.dvsmedeiros.commons.domain.Result;

@Component
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ApplicationFacade<T extends DomainEntity> implements IFacade<T>, ISpecificFacade<T> {

	@Autowired
	private Navigator<T> navigator;

	@Autowired
	private FactoryDAO factoryDAO;

	@Override
	public Result<T> save(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError()) {
			IDAO dao = factoryDAO.create(aEntity.getClass().getName());
			dao.save(aEntity);
			aCase.getResult().setEntity(aEntity);
		}
		return aCase.getResult();

	}

	@Override
	public Result<T> update(T aEntity, INavigationCase<T> aCase) {
		
		navigator.run(aEntity, aCase);
		if(!aCase.getResult().hasError()) {
			IDAO dao = factoryDAO.create(aEntity.getClass().getName());
			dao.update(aEntity);
			aCase.getResult().setEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> delete(T aEntity, INavigationCase<T> aCase) {
		
		if(aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)){
			IDAO dao = factoryDAO.create(aEntity.getClass().getName());
			dao.delete(aEntity);
			aCase.getResult().setEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> findAll(Class<? extends DomainEntity> clazz, INavigationCase<T> aCase) {

		if(aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)){
			IDAO dao = factoryDAO.create(clazz.getClass().getName());
			aCase.getResult().setEntityList(dao.findAll(clazz));
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> find(Long id, Class<? extends DomainEntity> clazz, INavigationCase<T> aCase) {
		
		if(aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)){
			IDAO dao = factoryDAO.create(clazz.getClass().getName());
			T aEntity = (T) dao.find(id, clazz);
			aCase.getResult().setEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> find(String code, Class<? extends T> clazz) {
		return null;
	}

	@Override
	public Result<T> delete(String code, Class<? extends T> clazz) {
		return null;
	}

	@Override
	public Result<T> findAll(boolean active, Class<? extends T> clazz) {
		return null;
	}

	@Override
	public Result<T> find(Filter<T> aFilter, INavigationCase<T> aCase) {
		return null;
	}

}

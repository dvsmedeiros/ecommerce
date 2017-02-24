package com.dvsmedeiros.commons.dao;

import java.util.List;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.IEntity;

public interface ISpecificDAO<T extends DomainEntity> extends IDAO<T>, IEntity {
	
	public T find(String code, Class<? extends T> clazz);
	
	public void delete(String code, Class<? extends T> clazz);
	
	public List<T> findAll(boolean active, Class<? extends T> clazz);
}

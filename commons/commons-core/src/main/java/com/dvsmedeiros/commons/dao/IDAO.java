package com.dvsmedeiros.commons.dao;

import java.util.List;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;
import com.dvsmedeiros.commons.domain.IEntity;

public interface IDAO<T extends DomainEntity> extends IEntity {

	public void save(T aEntity);

	public void update(T aEntity);

	public void delete(T aEntity);

	public List<T> findAll(Class<? extends T> clazz);

	public T find(Long id, Class<? extends T> clazz);
	
	public T find(String code, Class<? extends DomainSpecificEntity> clazz);
	
	public void delete(String code, Class<? extends DomainSpecificEntity> clazz);
	
	public List<T> findAll(boolean active, Class<? extends DomainSpecificEntity> clazz);

}

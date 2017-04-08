package com.dvsmedeiros.bce.dao;

import java.util.List;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.IEntity;

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

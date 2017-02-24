package com.dvsmedeiros.commons.dao;

import java.util.List;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.IEntity;

public interface IDAO<T extends DomainEntity> extends IEntity {

	public void save(T aEntity);

	public void update(T aEntity);

	public void delete(T aEntity);

	public List<T> findAll(Class<? extends T> clazz);

	public T find(Long id, Class<? extends T> clazz);

}

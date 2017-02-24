package com.dvsmedeiros.commons.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Result<T extends DomainEntity> extends ApplicationEntity {
	
	@Autowired
	private List<T> entityList;
	private T entity;
	private String message;
	private boolean error;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}
	
	public boolean hasError(){
		if(!error && message == null && entityList != null && entityList.size() > 0){
			return false;
		}
		return true;
	}
	
	public void setError() {
		this.error = true;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T aEntity) {
		this.entity = aEntity;
	}
	
}

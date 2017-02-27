package com.dvsmedeiros.commons.domain;

import java.util.ArrayList;
import java.util.List;

public class Result<T extends DomainEntity> extends ApplicationEntity {
	
	private List<T> entityList;
	private T entity;
	private String message;
	private boolean error;
	
	public Result() {
		this.entityList = new ArrayList<>();
	}
	
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
		if(!error && message == null && entityList != null){
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

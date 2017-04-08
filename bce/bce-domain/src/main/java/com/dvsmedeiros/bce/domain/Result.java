package com.dvsmedeiros.bce.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Result<T extends IEntity> extends ApplicationEntity {
	
	private List<T> entityList;
	private T entity;
	private Object uncheckedEntity;
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
	
	public void setUncheckedEntity(Object uncheckedEntity) {
		this.uncheckedEntity = uncheckedEntity;
	}
	
	public <R> R getUncheckedEntity() {
		return (R) uncheckedEntity;
	}
}

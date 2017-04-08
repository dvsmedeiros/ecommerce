package com.dvsmedeiros.bce.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.dao.ISpecificDAO;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class CodeValidator implements IStrategy<DomainSpecificEntity>{
	
	@Autowired
	@Qualifier("com.dvsmedeiros.bce.domain.DomainSpecificEntity")
	private ISpecificDAO<DomainSpecificEntity> dao;
	
	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {
		if(aEntity != null && aEntity.getCode() != null && !StringUtils.isEmpty(aEntity.getCode())){
			
			DomainSpecificEntity find = dao.find(aEntity.getCode(), aEntity.getClass());
			if(find != null ){
				aCase.suspendExecution();
				aCase.getResult().setMessage("Código duplicado!");
				return;
			}
			return; 
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Código inexistente ou inválido!");
		
	}

}

package com.dvsmedeiros.product.controller.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.product.domain.Product;

@Component
public class CodeValidator implements IStrategy<Product>{

	@Override
	public void process(Product entity, INavigationCase<Product> aCase) {
		
		if(entity != null && entity.getCode() != null && !StringUtils.isEmpty(entity.getCode())){
			aCase.getResult().getEntityList().add(entity);
			return; 
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Código inexistente ou inválido!");
	}

}

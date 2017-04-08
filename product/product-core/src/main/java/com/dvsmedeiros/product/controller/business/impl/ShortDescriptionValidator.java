package com.dvsmedeiros.product.controller.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.product.domain.Product;

@Component
public class ShortDescriptionValidator implements IStrategy<Product> {

	@Override
	public void process(Product aEntity, INavigationCase<Product> aCase) {
		
		if(aEntity != null && aEntity.getShortDescription() != null && !StringUtils.isEmpty(aEntity.getShortDescription())){
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Descri��o resumida inexistente ou inv�lida!");
		
	}

}

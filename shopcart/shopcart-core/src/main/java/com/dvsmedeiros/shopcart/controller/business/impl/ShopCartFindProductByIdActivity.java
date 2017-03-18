package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class ShopCartFindProductByIdActivity implements IStrategy<CartItem>{
	
	
	@Autowired
	@Qualifier("navigator")
	INavigator navigator;
	
	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
			
			if(aCase.getResult().getEntity() != null){
				return;
			}
		
			BusinessCase<Product> bCase = new BusinessCaseBuilder<Product>().withName("FIND_PRODUCT_BY_ID").build();
			navigator.run(aEntity.getProduct(), bCase);
			
			if(bCase.isSuspendExecution() || bCase.getResult().getEntity() == null){
				aCase.suspendExecution();
				aCase.getResult().setMessage(bCase.getResult().getMessage());
				return;
			}
			
			aEntity.setProduct(bCase.getResult().getEntity());
	}

}

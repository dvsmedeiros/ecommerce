package com.dvsmedeiros.freight.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class FreightFindProductByIdActivity implements IStrategy<Freight>{
	
	
	@Autowired
	@Qualifier("navigator")
	INavigator navigator;
	
	@Override
	public void process(Freight aEntity, INavigationCase<Freight> aCase) {
			
			BusinessCase<Product> bCase = new BusinessCaseBuilder<Product>().withName("FIND_PRODUCT_BY_ID").build();
			navigator.run(aEntity.getRequest().getProduct(), bCase);
			
			if(bCase.isSuspendExecution() || bCase.getResult().getEntity() == null){
				aCase.suspendExecution();
				aCase.getResult().setMessage(bCase.getResult().getMessage());
				return;
			}
			
			aEntity.getRequest().setProduct(bCase.getResult().getEntity());
	}

}

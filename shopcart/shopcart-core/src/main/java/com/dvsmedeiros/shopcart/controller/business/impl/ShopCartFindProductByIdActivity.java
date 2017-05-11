package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class ShopCartFindProductByIdActivity implements IStrategy<CartItem>{
	
	
	@Autowired
	@Qualifier("navigator")
	INavigator navigator;
	
	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
			
			CartItem item = aCase.getContext().getAttribute("item");
			if(item != null){
				return;
			}
		
			BusinessCase<Product> bCase = new BusinessCaseBuilder<Product>().withName("FIND_PRODUCT_BY_ID").build();
			navigator.run(aEntity.getProduct(), bCase);
			
			Product product = bCase.getContext().getAttribute("product");
			
			if(bCase.isSuspendExecution() || product == null){
				aCase.suspendExecution();
				aCase.getResult().setMessage(bCase.getResult().getMessage());
				return;
			}
			aEntity.setProduct(product);
	}

}

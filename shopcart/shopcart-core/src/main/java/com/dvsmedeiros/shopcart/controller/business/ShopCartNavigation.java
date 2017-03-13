package com.dvsmedeiros.shopcart.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.commons.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.shopcart.controller.business.impl.AddItemToCart;
import com.dvsmedeiros.shopcart.controller.business.impl.FindAndRemoveProductOfCart;
import com.dvsmedeiros.shopcart.controller.business.impl.ShopCartFindProductByIdActivity;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Configuration
public class ShopCartNavigation {
	
	@Autowired
	private ShopCartFindProductByIdActivity findProductByIdActivity;
	@Autowired
	private AddItemToCart addItemToCart;
	@Autowired
	private FindAndRemoveProductOfCart findAndRemoveProductOfCart;
	
	@Bean(name="ADD_ITEM_TO_CART")
	public EntityRuleDefinition<CartItem> addProductToCartNavigation(){
		
		EntityRuleDefinition<CartItem> activities = new EntityRuleDefinition<>();
		
		activities.addActivity(findProductByIdActivity);
		activities.addActivity(addItemToCart);
		
		return activities;
		
	}
	
	@Bean(name="REMOVE_ITEM_TO_CART")
	public EntityRuleDefinition<CartItem> removeProductToCartNavigation(){
		
		EntityRuleDefinition<CartItem> activities = new EntityRuleDefinition<>();
		
		activities.addActivity(findAndRemoveProductOfCart);
		
		return activities;
		
	}
}

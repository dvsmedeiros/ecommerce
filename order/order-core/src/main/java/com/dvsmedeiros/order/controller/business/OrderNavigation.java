package com.dvsmedeiros.order.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.order.controller.business.impl.AddOrderStatus;
import com.dvsmedeiros.order.controller.business.impl.CleanShopCart;
import com.dvsmedeiros.order.domain.Order;

@Configuration
public class OrderNavigation {

	@Autowired
	private AddOrderStatus addOrderStatus;
	@Autowired
	private CleanShopCart cleanShopCart;
	
	@Bean(name="SAVE_ORDER")
	public EntityRuleDefinition<Order> getSaveOrderNavigation(){
		
		EntityRuleDefinition<Order> activities = new EntityRuleDefinition<>();
		activities.addActivity(addOrderStatus);
		activities.addActivity(cleanShopCart);
		
		return activities;
	}

}

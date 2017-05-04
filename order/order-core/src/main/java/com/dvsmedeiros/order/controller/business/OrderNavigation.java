package com.dvsmedeiros.order.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.controller.business.impl.AddOrderStatus;
import com.dvsmedeiros.order.controller.business.impl.CleanShopCart;
import com.dvsmedeiros.order.controller.business.impl.FindFilterOrder;
import com.dvsmedeiros.order.controller.dao.OrderDAO;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.product.domain.Product;

@Configuration
public class OrderNavigation {

	@Autowired
	private AddOrderStatus addOrderStatus;
	@Autowired
	private CleanShopCart cleanShopCart;
	@Autowired
	private FindFilterOrder findFilterOrder;
	
	
	@Bean(name="com.dvsmedeiros.order.domain.Order")
	public OrderDAO getProductDAO(){
		return new OrderDAO();
	}
	
	@Bean(name="SAVE_ORDER")
	public EntityRuleDefinition<Order> getSaveOrderNavigation(){
		
		EntityRuleDefinition<Order> activities = new EntityRuleDefinition<>();
		activities.addActivity(addOrderStatus);
		activities.addActivity(cleanShopCart);
		
		return activities;
	}
	
	@Bean(name = "FIND_FILTER_ORDER")
	public EntityRuleDefinition<Filter<Order>> findFilterProduct() {

		EntityRuleDefinition<Filter<Order>> activities = new EntityRuleDefinition<>();

		activities.addActivity(findFilterOrder);

		return activities;
	}

}

package com.dvsmedeiros.order.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.controller.business.impl.AddOrderStatus;
import com.dvsmedeiros.order.controller.business.impl.CleanShopCart;
import com.dvsmedeiros.order.controller.business.impl.FindFilterOrder;
import com.dvsmedeiros.order.domain.Order;

@Configuration
public class OrderNavigation {

	@Autowired
	private AddOrderStatus addOrderStatus;
	@Autowired
	private CleanShopCart cleanShopCart;
	@Autowired
	private FindFilterOrder findFilterOrder;
	
	@Bean(name="CHECKOUT")
	public Navigation<Order> getSaveOrderNavigation(){
		
		return new NavigationBuilder<Order>()
		.next(addOrderStatus)
		.next(cleanShopCart)
		.build();
	}
	
	@Bean(name = "FIND_FILTER_ORDER")
	public Navigation<Filter<Order>> findFilterProduct() {

		return new NavigationBuilder<Filter<Order>>()
				.next(findFilterOrder)
				.build();
	}
}

package com.dvsmedeiros.freight.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.commons.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.freight.business.impl.CalculateFreight;
import com.dvsmedeiros.freight.business.impl.CalculateFreightForCart;
import com.dvsmedeiros.freight.business.impl.CalculateFreightForProduct;
import com.dvsmedeiros.freight.business.impl.FreightFindProductByIdActivity;
import com.dvsmedeiros.freight.business.impl.SummarizeShopCart;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.product.controller.business.impl.FindProductByIdActivity;
import com.dvsmedeiros.shopcart.domain.Cart;

@Configuration
public class FreightNavigation {
	
	@Autowired
	private SummarizeShopCart summarizeShopCart;
	@Autowired
	private FreightFindProductByIdActivity freightFindProductByIdActivity;
	@Autowired
	private CalculateFreight calculateFreight;
	@Autowired
	private CalculateFreightForCart calculateFreigthForCart;
	@Autowired
	private CalculateFreightForProduct calculateFreigthForProduct;
	
	@Bean(name="CALCULATE_FREIGHT")
	public EntityRuleDefinition<Freight> calculateFreightForProduct(){
		
		EntityRuleDefinition<Freight> activities = new EntityRuleDefinition<>();
			
		activities.addActivity(calculateFreight);
		
		return activities;
	}
	
	@Bean(name="CALCULATE_FREIGHT_FOR_PRODUCT")
	public EntityRuleDefinition<Freight> calculateFreightCart(){
		
		EntityRuleDefinition<Freight> activities = new EntityRuleDefinition<>();
			
		activities.addActivity(freightFindProductByIdActivity);
		activities.addActivity(calculateFreigthForProduct);
		
		return activities;
	}
	
	@Bean(name="CALCULATE_FREIGHT_FOR_CART")
	public EntityRuleDefinition<Cart> calculateFreightForCart(){
		
		EntityRuleDefinition<Cart> activities = new EntityRuleDefinition<>();
			
		activities.addActivity(summarizeShopCart);
		activities.addActivity(calculateFreigthForCart);
		
		return activities;
	}
}

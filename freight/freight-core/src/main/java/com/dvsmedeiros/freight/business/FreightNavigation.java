package com.dvsmedeiros.freight.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.freight.business.impl.CalculateFreight;
import com.dvsmedeiros.freight.business.impl.FreightFindProductByIdActivity;
import com.dvsmedeiros.freight.business.impl.SummarizeShopCart;
import com.dvsmedeiros.freight.domain.FreightFilter;

@Configuration
public class FreightNavigation {

	@Autowired
	private SummarizeShopCart summarizeShopCart;
	@Autowired
	private FreightFindProductByIdActivity freightFindProductByIdActivity;
	@Autowired
	private CalculateFreight calculateFreight;

	@Bean(name = "CALCULATE_FREIGHT")
	public EntityRuleDefinition<FreightFilter> calculateFreightForProduct() {

		EntityRuleDefinition<FreightFilter> activities = new EntityRuleDefinition<>();

		activities.addActivity(freightFindProductByIdActivity);
		activities.addActivity(summarizeShopCart);
		activities.addActivity(calculateFreight);

		return activities;
	}

}

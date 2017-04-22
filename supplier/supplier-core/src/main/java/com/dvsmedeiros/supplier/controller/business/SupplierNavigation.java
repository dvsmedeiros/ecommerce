package com.dvsmedeiros.supplier.controller.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.supplier.domain.Supplier;

@Configuration
public class SupplierNavigation {

	@Bean(name = "SAVE_SUPPLIER")
	public EntityRuleDefinition<Supplier> getSaveProductNavigation() {

		EntityRuleDefinition<Supplier> activities = new EntityRuleDefinition<>();
		return activities;
	}

}

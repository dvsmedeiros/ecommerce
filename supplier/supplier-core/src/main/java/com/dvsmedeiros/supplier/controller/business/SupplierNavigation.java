package com.dvsmedeiros.supplier.controller.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.supplier.domain.Supplier;

@Configuration
public class SupplierNavigation {

	@Bean(name = "SAVE_SUPPLIER")
	public Navigation<Supplier> getSaveProductNavigation() {
		
		return new NavigationBuilder<Supplier>()
					.build();
	}

}

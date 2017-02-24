package com.dvsmedeiros.product.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.commons.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.product.controller.business.impl.CodeValidator;
import com.dvsmedeiros.product.controller.dao.impl.ProductDAO;
import com.dvsmedeiros.product.domain.Product;

@Configuration
public class ProductNavigation {
	
	@Autowired
	private CodeValidator codeValidator;
	
	@Bean(name="com.dvsmedeiros.product.domain.Product")
	public ProductDAO getProductDAO(){
		return new ProductDAO();
	}
	
	@Autowired
	@Bean(name="SAVE_PRODUCT")
	public EntityRuleDefinition<Product> getSaveProductNavigation(EntityRuleDefinition<Product> activities){
		
		activities.addActivity(codeValidator);
		
		return activities;
	}
}

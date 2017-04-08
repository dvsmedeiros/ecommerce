package com.dvsmedeiros.product.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.controller.business.impl.ActiveValidator;
import com.dvsmedeiros.bce.controller.business.impl.CodeValidator;
import com.dvsmedeiros.bce.controller.business.impl.DescriptionValidator;
import com.dvsmedeiros.bce.controller.business.impl.IdValidator;
import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.product.controller.business.impl.FindProductByIdActivity;
import com.dvsmedeiros.product.controller.business.impl.ShortDescriptionValidator;
import com.dvsmedeiros.product.controller.dao.impl.ProductDAO;
import com.dvsmedeiros.product.domain.Category;
import com.dvsmedeiros.product.domain.Product;

@Configuration
public class ProductNavigation {
	
	@Autowired
	private IdValidator idValidator;
	@Autowired
	private CodeValidator codeValidator;
	@Autowired
	private DescriptionValidator descriptionValidator;
	@Autowired
	private ActiveValidator activeValidator;
	@Autowired
	private ShortDescriptionValidator shortDescriptionValidator;
	@Autowired
	private FindProductByIdActivity findProductByIdActivity;
	
	@Bean(name="com.dvsmedeiros.product.domain.Product")
	public ProductDAO getProductDAO(){
		return new ProductDAO();
	}
	
	@Bean(name="SAVE_PRODUCT")
	public EntityRuleDefinition<Product> getSaveProductNavigation(){
		
		EntityRuleDefinition<Product> activities = new EntityRuleDefinition<>();
		
		activities.addActivity(codeValidator);
		activities.addActivity(descriptionValidator);
		activities.addActivity(activeValidator);
		activities.addActivity(shortDescriptionValidator);
		
		return activities;
	}
	
	@Bean(name="FIND_PRODUCT_BY_ID")
	public EntityRuleDefinition<Product> findProductByIdNavigation(){
		
		EntityRuleDefinition<Product> activities = new EntityRuleDefinition<>();
		
		activities.addActivity(idValidator);
		activities.addActivity(findProductByIdActivity);
		
		return activities;
	}
	
	@Bean(name="SAVE_CATEGORY")
	public EntityRuleDefinition<Category> getSaveCategoryNavigation(){
		
		EntityRuleDefinition<Category> activities = new EntityRuleDefinition<>();
		
		activities.addActivity(codeValidator);
		activities.addActivity(descriptionValidator);
		activities.addActivity(activeValidator);
		
		return activities;
	}
	
	
}

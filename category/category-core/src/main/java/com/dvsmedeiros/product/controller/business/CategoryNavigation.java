package com.dvsmedeiros.product.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.ActiveValidator;
import com.dvsmedeiros.bce.core.controller.business.impl.CodeGenerator;
import com.dvsmedeiros.bce.core.controller.business.impl.CodeValidator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.category.domain.Category;
import com.dvsmedeiros.product.controller.business.impl.CategoryFilter;

@Configuration
public class CategoryNavigation {
	
	@Autowired
	private CodeValidator codeValidator;
	@Autowired
	private ActiveValidator activeValidator;	
	@Autowired
	private CategoryFilter categoryFilter;
	
	@Bean(name="SAVE_CATEGORY")
	public Navigation<Category> getSaveCategoryNavigation(){
		
		return new NavigationBuilder<Category>()
				.next(codeValidator)
				.next(activeValidator)
				.build();
	}
	
	@Bean(name="FILTER_CATEGORY")
	public Navigation<Filter<Category>> getFilterCategoryNavigation(){
		
		return new NavigationBuilder<Filter<Category>>()
				.next(categoryFilter)
				.build();
	}
}

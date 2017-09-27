package com.dvsmedeiros.product.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.ActiveValidator;
import com.dvsmedeiros.bce.core.controller.business.impl.CodeValidator;
import com.dvsmedeiros.bce.core.controller.business.impl.DescriptionValidator;
import com.dvsmedeiros.bce.core.controller.business.impl.IdValidator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.business.impl.FindFilterProduct;
import com.dvsmedeiros.product.controller.business.impl.FindProductByIdActivity;
import com.dvsmedeiros.product.controller.business.impl.ShortDescriptionValidator;
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
	@Autowired
	private FindFilterProduct findFilterProduct;
	
	@Bean(name="SAVE_RESPONSEMESSAGE")
	public Navigation<Product> getSaveResponseMessageNavigation(){
		
		return new NavigationBuilder<Product>()
				.build();
	}
	
	@Bean(name="SAVE_PRODUCT")
	public Navigation<Product> getSaveProductNavigation(){
		
		return new NavigationBuilder<Product>()
				.next(codeValidator)
				.next(descriptionValidator)
				.next(activeValidator)
				.next(shortDescriptionValidator)
				.build();
		
	}
	
	@Bean(name="FIND_PRODUCT_BY_ID")
	public Navigation<Product> findProductByIdNavigation(){
		
		return new NavigationBuilder<Product>()
				.next(idValidator)
				.next(findProductByIdActivity)
				.build();		
	}
	
	@Bean(name = "FIND_FILTER_PRODUCT")
	public Navigation<Filter<Product>> findFilterProduct() {

		return new NavigationBuilder<Filter<Product>>()
				.next(findFilterProduct)
				.build();

	}
	
}

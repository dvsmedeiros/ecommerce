package com.dvsmedeiros.product.service;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Result;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.product.domain.ProductResponse;

@Controller
public class ProductController {
	
	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Product> appFacade;
	
	@Autowired
	BusinessCaseBuilder<Product> aCase;
	
	@RequestMapping("product/create")
	public @ResponseBody Product product(){
		
		ProductResponse response = new ProductResponse();

		Product product = new Product();
		product.setActive(true);
		product.setCode("P01");
		product.setDescription("DESC P01");
		product.setInsertionDate(Calendar.getInstance());
		
		Result<Product> result = appFacade.save(product, aCase.withName("SAVE_PRODUCT").build() );
		
		if(result.hasError()){
			response.setCode("XPTO");
			response.setMessage( result.getMessage() );
		} 
		
		response.setProduct(product);
		return product;
	}
	
	@RequestMapping("product/list")
	public @ResponseBody List<Product> products(){
		
		Result<Product> result = appFacade.findAll(Product.class, aCase.build());
		
		return result.getEntityList();
		
	}
	
}

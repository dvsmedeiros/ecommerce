package com.dvsmedeiros.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.freight.domain.FreightService;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;

@Controller
public class FreightController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Freight> appFacade;
	
	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Autowired
	private Cart cart;

	@RequestMapping(value = "freight", method = RequestMethod.GET)
	public @ResponseBody List<FreightService> calculteFreightAndDeadLine() {
		
		BusinessCase<Cart> aCase = new BusinessCaseBuilder<Cart>().withName("CALCULATE_FREIGHT_FOR_CART").build();
		navigator.run(cart, aCase);
		
		if( aCase.isSuspendExecution() ){
			
			return new ArrayList<>();
		}
		
		return aCase.getResult().getEntity().getFreight().getResponse().getServices();
		
	}
	
	@RequestMapping(value = "freight/{productId}", method = RequestMethod.GET)
	public @ResponseBody List<FreightService> calculteFreightAndDeadLine(@PathVariable Long productId) {
		
		Freight freight = new Freight();
		Product product = new Product();
		product.setId(productId);
		freight.getRequest().setProduct(product);
		
		BusinessCase<Freight> aCase = new BusinessCaseBuilder<Freight>().withName("CALCULATE_FREIGHT_FOR_PRODUCT").build();
		navigator.run(freight, aCase);
		
		if( aCase.isSuspendExecution() ){
			
			return new ArrayList<>();
		}
		
		return aCase.getResult().getEntity().getResponse().getServices();
		
	}
}

package com.dvsmedeiros.checkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.checkout.domain.Checkout;
import com.dvsmedeiros.checkout.domain.PurchaseOrder;
import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.shopcart.domain.Cart;

@Controller
public class CheckoutController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Checkout> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Autowired
	private Cart cart;
	
	@RequestMapping("checkout")
	public @ResponseBody PurchaseOrder doCheckout(){
		
		return new PurchaseOrder();
	}
}

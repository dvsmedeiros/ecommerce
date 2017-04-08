package com.dvsmedeiros.shopcart.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.bce.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Controller
public class ShopCartController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Cart> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Autowired
	private Cart cart;

	@RequestMapping(value = "cart/product/{productId}", method = RequestMethod.POST)
	public @ResponseBody StatusResponse addProducToCart(@PathVariable Long productId) {
		
		StatusResponse response = new StatusResponse();
		
		CartItem item = new CartItem();
		item.getProduct().setId(productId);
		
		BusinessCase<CartItem> aCase = new BusinessCaseBuilder<CartItem>().withName("ADD_ITEM_TO_CART").build();
		navigator.run(item, aCase);
		
		if( aCase.isSuspendExecution() ){
			
			response.setCode(Status.ERROR);
			response.setMessage(aCase.getResult().getMessage());
			return response;
		}
		
		response.setCode(Status.OK);
		response.setMessage(item.getProduct().getDescription() + " adicionado ao carrinho!");
		
		return response;
		
	}
	
	@RequestMapping(value = "cart/product", method = RequestMethod.GET)
	public @ResponseBody Cart getShopCart(){
		
		return cart;
		
	}
	
	@RequestMapping(value = "cart/product/{productId}", method = RequestMethod.PUT)
	public @ResponseBody Cart removeCartItem(@PathVariable Long productId){
		
		CartItem item = new CartItem();
		item.getProduct().setId(productId);
		
		navigator.run(item, new BusinessCaseBuilder<CartItem>().withName("REMOVE_ITEM_TO_CART").build() );
		
		return cart;	
	}
	
	@RequestMapping(value = "cart/product/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody Cart removeALLCartItem(@PathVariable Long productId){
		
		CartItem item = new CartItem();
		item.getProduct().setId(productId);
		
		navigator.run(item, new BusinessCaseBuilder<CartItem>().withName("REMOVE_ALL_ITEM_TO_CART").build() );
		
		return cart;	
	}
}

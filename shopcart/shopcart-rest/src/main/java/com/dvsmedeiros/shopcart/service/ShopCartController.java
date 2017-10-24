package com.dvsmedeiros.shopcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Controller
public class ShopCartController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Cart> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;

	@Autowired
	private Cart cart;

	@RequestMapping(value = "cart/product/{productId}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addProducToCart(@PathVariable Long productId) {

		CartItem item = new CartItem();
		item.getProduct().setId(productId);

		BusinessCase<CartItem> aCase = new BusinessCaseBuilder<CartItem>().withName("ADD_ITEM_TO_CART").build();
		navigator.run(item, aCase);

		if (aCase.isSuspendExecution()) {
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, aCase.getResult().getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "cart/product", method = RequestMethod.GET)
	public @ResponseBody Cart getShopCart() {

		return cart;
	}

	@RequestMapping(value = "cart/product/{productId}", method = RequestMethod.PUT)
	public @ResponseBody Cart removeCartItem(@PathVariable Long productId) {

		CartItem item = new CartItem();
		item.getProduct().setId(productId);

		navigator.run(item, new BusinessCaseBuilder<CartItem>().withName("REMOVE_ITEM_TO_CART").build());

		return cart;
	}

	@RequestMapping(value = "cart/product/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody Cart removeALLCartItem(@PathVariable Long productId) {

		CartItem item = new CartItem();
		item.getProduct().setId(productId);

		navigator.run(item, new BusinessCaseBuilder<CartItem>().withName("REMOVE_ALL_ITEM_TO_CART").build());

		return cart;
	}
}

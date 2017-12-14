package com.dvsmedeiros.shopcart.service;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.User;
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
	private INavigator<Cart> navigator;
	@Autowired private Cart cart;
	@Autowired private ServletContext context;
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@RequestMapping(value = "cart/{productId}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addProducToCart(@PathVariable Long productId) {
		
		CartItem item = new CartItem();
		item.getProduct().setId(productId);

		BusinessCase<CartItem> aCase = new BusinessCaseBuilder<CartItem>().withName("ADD_ITEM_TO_CART").build();
		navigator.run(item, aCase);

		if (aCase.isSuspendExecution()) {
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, aCase.getResult().getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		blocksItemTemporarily(item);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getShopCart() {

		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "cart/{productId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> removeCartItem(@PathVariable Long productId) {

		CartItem item = new CartItem();
		item.getProduct().setId(productId);

		navigator.run(item, new BusinessCaseBuilder<CartItem>().withName("REMOVE_ITEM_TO_CART").build());

		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "cart/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> removeALLCartItem(@PathVariable Long productId) {

		CartItem item = new CartItem();
		item.getProduct().setId(productId);

		navigator.run(item, new BusinessCaseBuilder<CartItem>().withName("REMOVE_ALL_ITEM_TO_CART").build());

		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "cart/cupom/{cupomCode}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addCupom(@PathVariable String cupomCode) {
		
		BusinessCase<CartItem> aCase = new BusinessCaseBuilder<CartItem>().withName("ADD_CUPOM_TO_CART").build();
		aCase.getContext().setAttribute("logged", getLoggedClient());
		navigator.run(new Cupom(cupomCode), aCase);

		if (aCase.isSuspendExecution() || aCase.getResult().hasError()) {
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, aCase.getResult().getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	@RequestMapping(value = "cart/cupom/{cupomCode}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> removeCupom(@PathVariable String cupomCode) {

		BusinessCase<CartItem> aCase = new BusinessCaseBuilder<CartItem>().withName("REMOVE_CUPOM_TO_CART").build();
		navigator.run(new Cupom(cupomCode), aCase);

		if (aCase.isSuspendExecution() || aCase.getResult().hasError()) {
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, aCase.getResult().getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	@RequestMapping(value = "cart", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> cleanCart() {
		cart.cleanCart();
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	private void blocksItemTemporarily(CartItem item) {
		Long quantity = (Long) context.getAttribute(item.getProduct().getCode());
		if(quantity != null) {
			context.setAttribute(item.getProduct().getCode(), ++quantity);
		} else {
			context.setAttribute(item.getProduct().getCode(), new Long(1));
		}
	}
	
	private void unblockItemTemporarily(CartItem item) {
		return;
	}
	
	public Client getLoggedClient() {
		User loggedUser = getLoggedUser();
		return dao.findClientByUser(loggedUser.getId());
	}
	
	public User getLoggedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}

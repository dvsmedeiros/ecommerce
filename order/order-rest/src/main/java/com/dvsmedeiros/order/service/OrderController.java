package com.dvsmedeiros.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Order> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;

	@Autowired
	private User loggedUser;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity checkout(@RequestBody Order order) {

		try {
			
			if (loggedUser != null && loggedUser.getId() > 0) {
				order.setUser(loggedUser);
			}

			Result result = appFacade.save(order, new BusinessCaseBuilder().withName("CHECKOUT").build());

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar pedido."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getOrders() {

		try {

			Filter<Order> filter = new Filter<>(Order.class);

			if (loggedUser != null && loggedUser.getId() > 0) {
				filter.getEntity().setUser(loggedUser);
			}

			Result result = appFacade.find(filter, new BusinessCaseBuilder().withName("FIND_FILTER_ORDER").build());
			List<Order> orders =  result.getEntity("orders");
			
			return new ResponseEntity(orders, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "orders/{orderId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getProductById(@PathVariable Long orderId) {

		try {

			Result result = appFacade.find(orderId, Order.class);
			Order order = result.getEntity();
			return new ResponseEntity(order, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

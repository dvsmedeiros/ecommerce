package com.dvsmedeiros.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("${bce.app.context}/order")
public class OrderController<T extends Order> extends CommonsController<T> {

	public OrderController() {
		super((Class<? extends T>) Order.class);
	}

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Order> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity checkout(@RequestBody Order order) {

		try {
			
			User loggedUser = getLoggedUser();
			order.setUser(loggedUser);
			
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
	
	@Override
	public @ResponseBody ResponseEntity findEntityByFilter(@RequestBody Filter<T> filter) {
		
		User loggedUser = getLoggedUser();
		filter.getEntity().setUser(loggedUser);	
		return super.findEntityByFilter(filter);
		
	}
	
}

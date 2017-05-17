package com.dvsmedeiros.order.service;

import java.util.List;

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
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.order.domain.Order;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Order> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Autowired
	private User loggedUser;
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public @ResponseBody StatusResponse checkout(@RequestBody Order order) {

		StatusResponse response = new StatusResponse();
		
		try {
			
			if(loggedUser != null && loggedUser.getId() > 0){
				order.setUser(loggedUser);
			}
			
			Result result = appFacade.save(order, new BusinessCaseBuilder().withName("CHECKOUT").build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Pedido cadastrado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar pedido.");
		}

		return response;
	}
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public @ResponseBody List<Order> getOrders(){
		
		Result result = null;

		try {
			
			Filter<Order> filter = new Filter<>(Order.class);
			
			if(loggedUser != null && loggedUser.getId() > 0){
				filter.getEntity().setUser(loggedUser);
			}
			
			result = appFacade.find(filter, new BusinessCaseBuilder().withName("FIND_FILTER_ORDER").build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity("orders");
	}
	
	@RequestMapping(value = "orders/{orderId}", method = RequestMethod.GET)
	public @ResponseBody Order getProductById(@PathVariable Long orderId) {

		Result result = null;

		try {

			result = appFacade.find(orderId, Order.class, new BusinessCaseBuilder<Order>().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity();
	}
	
}

package com.dvsmedeiros.order.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.order.domain.Order;

@Controller
public class OrderController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Order> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	

}

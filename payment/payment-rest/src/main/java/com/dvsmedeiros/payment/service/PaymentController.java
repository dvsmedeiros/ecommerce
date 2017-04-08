package com.dvsmedeiros.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.payment.domain.Payment;

@Controller
public class PaymentController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Payment> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
}

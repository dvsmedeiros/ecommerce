package com.dvsmedeiros.stock.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.stock.domain.Stock;

@Controller
public class StockController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Stock> appFacade;

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	

}

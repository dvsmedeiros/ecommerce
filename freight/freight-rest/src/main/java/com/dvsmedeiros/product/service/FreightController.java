package com.dvsmedeiros.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.freight.domain.Freight;

@Controller
public class FreightController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Freight> appFacade;

}

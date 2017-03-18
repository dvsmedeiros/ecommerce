package com.dvsmedeiros.product.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.freight.domain.FreightResponse;

@Controller
public class FreightController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Freight> appFacade;

	@RequestMapping(value = "freight", method = RequestMethod.GET)
	public @ResponseBody List<FreightResponse> calculteFreightAndDeadLine() {
		return null;
	}
}

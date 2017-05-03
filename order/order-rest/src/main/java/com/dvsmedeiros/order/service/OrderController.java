package com.dvsmedeiros.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.INavigator;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;
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

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public @ResponseBody StatusResponse checkout(@RequestBody Order order) {

		StatusResponse response = new StatusResponse();

		try {

			Result<Order> result = appFacade.save(order, new BusinessCaseBuilder().withName("SAVE_ORDER").build());

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
}

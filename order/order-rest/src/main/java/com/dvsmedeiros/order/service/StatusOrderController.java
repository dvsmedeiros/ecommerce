package com.dvsmedeiros.order.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.order.domain.StatusOrder;

@Controller
@RequestMapping("${bce.app.context}/statusOrder")
public class StatusOrderController extends CommonsController<StatusOrder> {

	public StatusOrderController() {
		super(StatusOrder.class);
	}
	
}

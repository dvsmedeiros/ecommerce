package com.dvsmedeiros.order.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.order.domain.StatusOrder;

@Controller
@RequestMapping("${bce.app.context}/statusOrder")
@SuppressWarnings("unchecked")
public class StatusOrderController<T extends StatusOrder> extends CommonsController<T> {

	public StatusOrderController() {
		super((Class<? extends T>) StatusOrder.class);
	}
	
}

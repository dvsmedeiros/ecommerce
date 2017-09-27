package com.dvsmedeiros.product.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.product.domain.PriceGroup;
import com.dvsmedeiros.rest.rest.controller.DomainEntityController;

@Controller
@RequestMapping("${bce.app.context}/price-group")
public class PriceGroupController extends DomainEntityController<PriceGroup>{

	public PriceGroupController() {
		super(PriceGroup.class);
	}

}

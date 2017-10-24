package com.dvsmedeiros.address.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.address.domain.AddressType;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;


@Controller
@RequestMapping("${bce.app.context}/addressType")
public class AddressTypeController extends DomainSpecificEntityController<AddressType> {

	public AddressTypeController() {
		super(AddressType.class);
	}
	
}

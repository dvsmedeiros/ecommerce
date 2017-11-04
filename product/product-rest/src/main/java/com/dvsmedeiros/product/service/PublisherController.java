package com.dvsmedeiros.product.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.product.domain.Publisher;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/publisher")
public class PublisherController extends DomainSpecificEntityController<Publisher>{

	public PublisherController() {
		super(Publisher.class);
	}
	
}

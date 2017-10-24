package com.dvsmedeiros.commons.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.commons.domain.Reason;
import com.dvsmedeiros.rest.rest.controller.DomainEntityController;

@Controller
@RequestMapping("${bce.app.context}/reason")
public class ReasonController extends DomainEntityController<Reason>{
	
	public ReasonController() {
		super(Reason.class);
	}

}

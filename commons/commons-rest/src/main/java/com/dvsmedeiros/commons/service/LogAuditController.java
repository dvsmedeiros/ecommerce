package com.dvsmedeiros.commons.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.bce.domain.DomainEntity;
//import com.dvsmedeiros.bce.domain.Audit;
import com.dvsmedeiros.rest.rest.controller.DomainEntityController;

@Controller
@RequestMapping("${bce.app.context}/audit")
public class LogAuditController extends DomainEntityController<DomainEntity>{
//public class LogAuditController extends DomainEntityController<Audit>{

	public LogAuditController() {
		//super(Audit.class);
		super(DomainEntity.class);
	}
	
}

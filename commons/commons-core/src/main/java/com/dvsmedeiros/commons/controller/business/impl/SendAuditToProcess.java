package com.dvsmedeiros.commons.controller.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.utils.BceUtils;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.DomainEntity;
//import com.dvsmedeiros.bce.domain.Audit;

@Component
public class SendAuditToProcess extends ApplicationEntity implements IStrategy<DomainEntity> {

	@Override
	public void process(DomainEntity aEntity, INavigationCase<DomainEntity> aCase) {
		// TODO Auto-generated method stub
		
	}
//public class SendAuditToProcess extends ApplicationEntity implements IStrategy<Audit> {
	
	//@Autowired
	//private JmsTemplate jmsTemplate;
	
	//@Override
	//public void process(Audit aEntity, INavigationCase<Audit> aCase) {
	//	
	//	String aMessage = BceUtils.toJson(aEntity);
    //    jmsTemplate.convertAndSend("audit", aMessage);
	//}
}

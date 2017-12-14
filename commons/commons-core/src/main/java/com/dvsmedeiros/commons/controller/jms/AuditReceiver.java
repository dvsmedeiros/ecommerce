package com.dvsmedeiros.commons.controller.jms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.core.utils.BceUtils;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Audit;

@Component
public class AuditReceiver extends ApplicationEntity {

	@Qualifier("genericDAO")
	private GenericDAO<Audit> dao;

	@JmsListener(destination = "audit", containerFactory = "myFactory")
	public void receiveOrder(String aMessage) {
		Audit fromJson = (Audit) BceUtils.fromJson(aMessage, Audit.class);
		System.out.println(fromJson);
	}
}

package com.dvsmedeiros.commons.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.dao.INotificationDAO;
import com.dvsmedeiros.commons.controller.dao.impl.CupomDAO;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.Notification;

@Component
public class FindFilterNotification implements IStrategy<Filter<Notification>> {
	
	@Autowired
	private INotificationDAO dao;
	
	@Override
	public void process(Filter<Notification> aFilter, INavigationCase<Filter<Notification>> aCase) {
		
		List<Notification> cupons = dao.filter(aFilter);
		aCase.getResult().addEntity(Result.RESULTS_KEY, cupons);
		
	}

}

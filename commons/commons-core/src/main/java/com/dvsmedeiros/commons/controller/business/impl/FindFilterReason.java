package com.dvsmedeiros.commons.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.dao.IReasonDAO;
import com.dvsmedeiros.commons.domain.Reason;

@Component
public class FindFilterReason extends ApplicationEntity implements IStrategy<Filter<Reason>>{
	
	@Autowired
	@Qualifier("reasonDAO")
	private IReasonDAO dao;

	@Override
	public void process(Filter<Reason> aEntity, INavigationCase<Filter<Reason>> aCase) {
		
		List<Reason> reasons = dao.filter(aEntity);
		aCase.getResult().addEntity(Result.RESULTS_KEY, reasons);
		
	}
	

}

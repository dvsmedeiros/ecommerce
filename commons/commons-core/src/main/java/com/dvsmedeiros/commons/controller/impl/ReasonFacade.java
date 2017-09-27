package com.dvsmedeiros.commons.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.impl.ApplicationFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.controller.impl.Navigator;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.IReasonFacade;
import com.dvsmedeiros.commons.domain.Reason;
import com.dvsmedeiros.commons.domain.ReasonType;
import com.google.common.base.Strings;

@Component
public class ReasonFacade<T extends DomainSpecificEntity> extends ApplicationFacade<T> implements IReasonFacade<T> {
	
	@Autowired
	private Navigator<T> navigator;

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<T> repository;

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Reason> reasonRepository;
	
	@Override
	public Result inactivate(Class<? extends DomainSpecificEntity> clazz, String code, Reason reason) {

		BusinessCase<Reason> aCase = new BusinessCaseBuilder<Reason>().withName("SAVE_REASON").build();

		if (!Strings.isNullOrEmpty(code)) {
			
			repository.inactivate(clazz, code);
			reason.setType(ReasonType.INACTIVATION);
			reason.setCode(code);
			navigator.run(reason, aCase);
			
			if(!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
				reasonRepository.save(reason);				
			}
			
			return aCase.getResult();
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Código inexistente ou inválido!");
		return aCase.getResult();
	}

	@Override
	public Result activate(Class<? extends DomainSpecificEntity> clazz, String code, Reason reason) {
		
		BusinessCase<Reason> aCase = new BusinessCaseBuilder<Reason>().withName("SAVE_REASON").build();

		if (!Strings.isNullOrEmpty(code)) {
			
			repository.activate(clazz, code);
			reason.setType(ReasonType.ACTIVIVATION);
			reason.setCode(code);
			navigator.run(reason, aCase);
			
			if(!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
				reasonRepository.save(reason);				
			}
			
			return aCase.getResult();
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Código inexistente ou inválido!");
		return aCase.getResult();
	}

}

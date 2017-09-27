package com.dvsmedeiros.commons.controller;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.Reason;

public interface IReasonFacade<T extends DomainSpecificEntity> extends IFacade<T>{
	
	Result inactivate(Class<? extends DomainSpecificEntity> clazz, String code, Reason reason);
	
	Result activate(Class<? extends DomainSpecificEntity> clazz, String code, Reason reason);
	
}

package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.order.domain.Cupom;
import com.dvsmedeiros.order.domain.CupomType;
import com.dvsmedeiros.order.domain.Order;

@Component
public class OrderCreateCupomExchange extends ApplicationEntity implements IStrategy<Order> {
	
	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Cupom> appFacade;
	
	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
		
		Cupom cupom = new Cupom();
		cupom.setOwner(aEntity.getUser());
		cupom.setValue(aEntity.getTotal().doubleValue());
		
		BusinessCase<Cupom> bCase = new BusinessCaseBuilder<Cupom>().withName("CREATE_CUPOM#".concat(CupomType.EXCHANGE.name())).build();
		appFacade.save(cupom, bCase);
		
		if(bCase.isSuspendExecution() || bCase.getResult().hasError() ) {
			aCase.suspendExecution("Erro na criação do cupom de troca!");
		}
		
	}
}

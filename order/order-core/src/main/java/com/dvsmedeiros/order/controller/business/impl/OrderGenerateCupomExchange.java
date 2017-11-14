package com.dvsmedeiros.order.controller.business.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.controller.ICouponFiller;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.CupomType;
import com.dvsmedeiros.order.domain.Order;

@Component
public class OrderGenerateCupomExchange extends ApplicationEntity implements IStrategy<Order> {

	@Qualifier("applicationFacade")
	@Autowired
	private IFacade<Cupom> appFacade;

	@Qualifier("navigator")
	@Autowired
	private INavigator<Order> navigator;

	@Autowired
	private Map<String, ICouponFiller> map;

	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {

		ICouponFiller filler = map.get(aEntity.getStatusOrder().getCode());
		BusinessCase<Cupom> cCase = new BusinessCaseBuilder<Cupom>().build();
		cCase.getContext().setAttribute("order", aEntity);
		filler.process(new Cupom(), cCase);
		
		Cupom coupon = cCase.getContext().getAttribute("coupon");

		if (coupon != null) {
			BusinessCase<Cupom> bCase = new BusinessCaseBuilder<Cupom>().withName("CREATE_CUPOM#".concat(CupomType.EXCHANGE.name())).build();
			appFacade.save(coupon, bCase);

			if (bCase.isSuspendExecution() || bCase.getResult().hasError()) {
				aCase.suspendExecution("Erro na criação do cupom de troca!");
			}
		}
	}
}

package com.dvsmedeiros.commons.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.CodeGenerator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.business.impl.CouponOwnerValidator;
import com.dvsmedeiros.commons.controller.business.impl.CreateCupomExchange;
import com.dvsmedeiros.commons.controller.business.impl.FindCupom;
import com.dvsmedeiros.commons.controller.business.impl.FindFilterCupom;
import com.dvsmedeiros.commons.controller.business.impl.IsCupomPresentValidator;
import com.dvsmedeiros.commons.controller.business.impl.QuantityCupomValidator;
import com.dvsmedeiros.commons.controller.business.impl.TotalValueCupomValidator;
import com.dvsmedeiros.commons.domain.Cupom;

@Configuration
public class CupomNavigation {

	@Autowired private FindFilterCupom findFilterCupom;
	@Autowired private CodeGenerator codeGenerator;
	@Autowired private CreateCupomExchange createCupomExchange;
	@Autowired private FindCupom findCupom;
	@Autowired private TotalValueCupomValidator totalValueCupomValidator;
	@Autowired private IsCupomPresentValidator isCupomPresent;
	@Autowired private CouponOwnerValidator couponOwnerValidator;
	@Autowired private QuantityCupomValidator quantityCupomValidator;
	
	@Bean(name = "CREATE_CUPOM#EXCHANGE")
	public Navigation<Cupom> createCupom() {

		return new NavigationBuilder<Cupom>()
				.next(codeGenerator)
				.next(createCupomExchange)
				.build();
	}
	
	@Bean(name = "COUPON_VALIDATOR")
	public Navigation<Cupom> couponValidator() {

		return new NavigationBuilder<Cupom>()
				.next(findCupom)
				.next(isCupomPresent)
				.next(couponOwnerValidator)
				.next(quantityCupomValidator)
				.next(totalValueCupomValidator)
				.build();
	}
	
	@Bean(name = "FILTER_CUPOM")
	public Navigation<Filter<Cupom>> findFilterCupom() {

		return new NavigationBuilder<Filter<Cupom>>()
				.next(findFilterCupom)
				.build();
	}
	
}

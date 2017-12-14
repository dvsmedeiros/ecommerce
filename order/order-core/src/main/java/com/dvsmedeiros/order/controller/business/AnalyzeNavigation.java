package com.dvsmedeiros.order.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Analyze;
import com.dvsmedeiros.order.controller.business.impl.FindFilterPercentSaleByCategoryGenderAge;
import com.dvsmedeiros.order.controller.business.impl.FindFilterQuantitySaleByCategoryPeriod;

@Configuration
public class AnalyzeNavigation {

	@Autowired
	private FindFilterPercentSaleByCategoryGenderAge percentSaleByCategoryGenderAge;
	@Autowired
	private FindFilterQuantitySaleByCategoryPeriod quantitySaleByCategoryPeriod;
				  
	@Bean(name = "FILTER_ANALYZE#GRAPH_0001")
	public Navigation<Filter<Analyze>> findFilterGraph0001() {

		return new NavigationBuilder<Filter<Analyze>>()
				.next(percentSaleByCategoryGenderAge)
				.build();
	}
	
	@Bean(name = "FILTER_ANALYZE#GRAPH_0002")
	public Navigation<Filter<Analyze>> findFilterGraph0002() {

		return new NavigationBuilder<Filter<Analyze>>()
				.next(quantitySaleByCategoryPeriod)
				.build();
	}
}

package com.dvsmedeiros.order.controller.dao;

import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Analyze;
import com.dvsmedeiros.commons.domain.PercentSaleByCategoryGenderAgeBean;
import com.dvsmedeiros.commons.domain.QuantitySaleByCategoryPeriodBean;

public interface IAnalyzeDAO extends IDAO<Analyze> {

	List<PercentSaleByCategoryGenderAgeBean> percentSaleByCategoryGenderAge(Filter<Analyze> aEntity);
	List<QuantitySaleByCategoryPeriodBean> quantitySaleByCategoryPeriod(Filter<Analyze> aEntity);
}

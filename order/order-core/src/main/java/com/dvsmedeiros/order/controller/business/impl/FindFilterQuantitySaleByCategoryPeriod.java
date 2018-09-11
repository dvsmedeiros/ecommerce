package com.dvsmedeiros.order.controller.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.Analyze;
import com.dvsmedeiros.commons.domain.QuantitySaleByCategoryPeriodBean;
import com.dvsmedeiros.commons.domain.QuantitySaleByCategoryPeriodVO;
import com.dvsmedeiros.order.controller.dao.IAnalyzeDAO;

@Component
public class FindFilterQuantitySaleByCategoryPeriod implements IStrategy<Filter<Analyze>> {

	@Autowired
	private IAnalyzeDAO dao;

	@Override
	public void process(Filter<Analyze> aFilter, INavigationCase<Filter<Analyze>> aCase) {
		
		List<QuantitySaleByCategoryPeriodBean> beans = dao.quantitySaleByCategoryPeriod(aFilter);
		
		Map<String, List<QuantitySaleByCategoryPeriodVO>> groupByMonth = new HashMap<>();
		for (QuantitySaleByCategoryPeriodBean r : beans) {

			if (groupByMonth.containsKey(r.getCategory())) {
				List<QuantitySaleByCategoryPeriodVO> list = groupByMonth.get(r.getCategory());
				QuantitySaleByCategoryPeriodVO bean = new QuantitySaleByCategoryPeriodVO();
				process(bean, r);
				list.add(bean);
				process(bean, r);
			} else {
				List<QuantitySaleByCategoryPeriodVO> list = new ArrayList<>();
				QuantitySaleByCategoryPeriodVO bean = new QuantitySaleByCategoryPeriodVO();
				process(bean, r);
				list.add(bean);
				groupByMonth.put(r.getCategory(), list);
			}
		}
		
		aCase.getResult().addEntity(Result.RESULTS_KEY, Collections.EMPTY_LIST);
		
	}

	private void process(QuantitySaleByCategoryPeriodVO bean, QuantitySaleByCategoryPeriodBean result) {
		bean.setCategory(result.getCategory());
		bean.setMonth(result.getMonth());
		bean.setQuantity(result.getQuantity());
	}
}

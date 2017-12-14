package com.dvsmedeiros.order.controller.business.impl;

import java.util.ArrayList;
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
import com.dvsmedeiros.commons.domain.Gender;
import com.dvsmedeiros.commons.domain.PercentSaleByCategoryGenderAgeBean;
import com.dvsmedeiros.commons.domain.PercentSaleByCategoryGenderAgeVO;
import com.dvsmedeiros.order.controller.dao.IAnalyzeDAO;

@Component
public class FindFilterPercentSaleByCategoryGenderAge implements IStrategy<Filter<Analyze>> {
	
	@Autowired
	private IAnalyzeDAO dao;
	
	@Override
	public void process(Filter<Analyze> aFilter, INavigationCase<Filter<Analyze>> aCase) {
		
		List<PercentSaleByCategoryGenderAgeBean> beans = dao.percentSaleByCategoryGenderAge(aFilter);
		
		Map<String, PercentSaleByCategoryGenderAgeVO> map = new HashMap<>();
		for (PercentSaleByCategoryGenderAgeBean r : beans) {
			
			if(map.containsKey(r.getCategory())) {
				PercentSaleByCategoryGenderAgeVO bean = map.get(r.getCategory());
				process(bean, r);
			} else {
				PercentSaleByCategoryGenderAgeVO bean = new PercentSaleByCategoryGenderAgeVO();
				process(bean, r);				
				map.put(r.getCategory(), bean);
			}
		}
		
		List<PercentSaleByCategoryGenderAgeVO> result = new ArrayList<>();
		for(String key : map.keySet()) {
			result.add(map.get(key));
		}
		aCase.getResult().addEntity(Result.RESULTS_KEY, result);
		
	}
	
	private void process(PercentSaleByCategoryGenderAgeVO bean, PercentSaleByCategoryGenderAgeBean result) {
		bean.setCategory(result.getCategory());
		if (result.getGender().equals(Gender.FEMALE)) {
			bean.setFemale(Double.valueOf(result.getQuantity()));
		} else if (result.getGender().equals(Gender.MALE)) {
			bean.setMale(Double.valueOf(result.getQuantity()));
		} else if (result.getGender().equals(Gender.OTHER)) {
			bean.setOther(Double.valueOf(result.getQuantity()));
		}
		bean.setAverageAge(result.getAverageAge());
	}
}

package com.dvsmedeiros.commons.service;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.Analyze;
import com.dvsmedeiros.commons.domain.PercentSaleByCategoryGenderAgeVO;
import com.dvsmedeiros.commons.domain.QuantitySaleByCategoryPeriodVO;

@Controller
@RequestMapping("${bce.app.context}/analyze")
public class AnalyzeController extends CommonsController<Analyze> {

	public AnalyzeController() {
		super(Analyze.class);
	}

	@Override
	public @ResponseBody ResponseEntity<?> findEntityByFilter(@RequestBody Filter<Analyze> filter, boolean logged) {

		try {
			
			boolean code = filter != null && filter.getEntity() != null && filter.getEntity().getCode() != null && !filter.getEntity().getCode().isEmpty();
			
			if(!code) throw new Exception();
			
			BusinessCase<Filter<Analyze>> bCase = new BusinessCaseBuilder<Filter<Analyze>>()
					.withName("FILTER_ANALYZE#".concat(filter.getEntity().getCode().toUpperCase())).build();
					
			Result result = appFacade.find(filter, bCase);
			if (filter.getEntity().getCode().equals(Analyze.PERCENT_SALE_BY_CATEGORY_GENDER_AGE)) {
				List<PercentSaleByCategoryGenderAgeVO> ts = result.getEntities();
				return new ResponseEntity<>(ts == null ? Collections.emptyList() : ts, HttpStatus.OK);
			} else if (filter.getEntity().getCode().equals(Analyze.PERCENT_SALE_BY_CATEGORY_GENDER_AGE)) {
				List<QuantitySaleByCategoryPeriodVO> ts = result.getEntities();
				return new ResponseEntity<>(ts == null ? Collections.emptyList() : ts, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

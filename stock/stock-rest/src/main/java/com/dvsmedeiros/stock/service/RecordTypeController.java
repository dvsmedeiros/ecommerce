package com.dvsmedeiros.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.rest.rest.controller.BaseController;
import com.dvsmedeiros.stock.domain.RecordType;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RecordTypeController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<RecordType> appFacade;

	@Cacheable(value = "cacheRecordTypes")
	@RequestMapping(value = "recordType", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RecordType>> getProductCategories(@RequestParam(value = "active", required = false) Boolean active) {

		try {
			
			Result result;
			
			if(active != null) {
				result = appFacade.findAll(RecordType.class, active, new BusinessCaseBuilder().build());
			} else {
				result = appFacade.findAll(RecordType.class, new BusinessCaseBuilder().build());
			}
			
			List<RecordType> recordTypes = result.getEntities();
			
			return new ResponseEntity<>(recordTypes, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
